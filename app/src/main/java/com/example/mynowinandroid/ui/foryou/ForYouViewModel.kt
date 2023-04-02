package com.example.mynowinandroid.ui.foryou

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.mynowinandroid.data.news.NewsRepository
import com.example.mynowinandroid.data.news.NewsResource
import com.example.mynowinandroid.data.news.Topic
import com.example.mynowinandroid.data.news.TopicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val topicsRepository: TopicsRepository,
    private val newsRepository: NewsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val followedTopicsStateFlow = topicsRepository.getFollowedTopicsIdsStream()
        .map { followedTopics ->
            if (followedTopics.isEmpty()) {
                FollowedTopicsState.None
            } else {
                FollowedTopicsState.FollowedTopics(topics = followedTopics)
            }
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FollowedTopicsState.Unknown,
        )

    private var inProgressTopicSelection by savedStateHandle.saveable {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val uiState: StateFlow<ForYouFeedUiState> = combine(
        followedTopicsStateFlow,
        topicsRepository.getTopicsStream(),
        snapshotFlow { inProgressTopicSelection },
    ) { followedTopicsUserState, availableTopics, inProgressTopicSelection ->
        when (followedTopicsUserState) {
            FollowedTopicsState.Unknown -> flowOf<ForYouFeedUiState>(ForYouFeedUiState.Loading)
            is FollowedTopicsState.FollowedTopics -> {
                newsRepository.getNewsResourcesStream(
                    filterTopicsIds = followedTopicsUserState.topics,
                ).map { feed ->
                    ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicsSelection(
                        feed = feed,
                    )
                }
            }
            FollowedTopicsState.None -> {
                newsRepository.getNewsResourcesStream(filterTopicsIds = inProgressTopicSelection)
                    .map { feed ->
                        ForYouFeedUiState.PopulatedFeed.FeedWithTopicsSelection(
                            selectedTopics = availableTopics.map { topic ->
                                topic to (topic.id in inProgressTopicSelection)
                            },
                            feed = feed,
                        )
                    }
            }
        }
    }.flatMapLatest { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ForYouFeedUiState.Loading,
        )

    fun updateTopicSelection(topicId: Int, isChecked: Boolean) {
        withMutableSnapshot {
            inProgressTopicSelection =
                if (isChecked) {
                    inProgressTopicSelection + topicId
                } else {
                    inProgressTopicSelection - topicId
                }
        }
    }

    fun saveFollowedTopics(){
        if(inProgressTopicSelection.isEmpty()) return
        viewModelScope.launch {
            topicsRepository.setFollowedTopicIds(inProgressTopicSelection)
        }
    }

}

/**
 * A sealed hierarchy for the user's current followed topics state.
 */
private sealed interface FollowedTopicsState {
    /**
     * The current state is unknown (hasn't loaded yet)
     */
    object Unknown : FollowedTopicsState

    /**
     * The user has n't followed any topics yet.
     */
    object None : FollowedTopicsState

    /**
     * The user has followed the given (non-empty) set of [topics].
     */
    data class FollowedTopics(
        val topics: Set<Int>,
    ) : FollowedTopicsState
}

/**
 * A sealed hierarchy describing the for you screen state
 */
sealed interface ForYouFeedUiState {
    /**
     * The screen is still loading
     */
    object Loading : ForYouFeedUiState

    sealed interface PopulatedFeed : ForYouFeedUiState {

        val feed: List<NewsResource>

        data class FeedWithTopicsSelection(
            val selectedTopics: List<Pair<Topic, Boolean>>,
            override val feed: List<NewsResource>,
        ) : PopulatedFeed {
            val canSaveSelectedTopics: Boolean = selectedTopics.any { it.second }
        }

        data class FeedWithoutTopicsSelection(
            override val feed: List<NewsResource>,
        ) : PopulatedFeed
    }
}
