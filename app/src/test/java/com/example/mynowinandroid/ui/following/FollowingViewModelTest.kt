package com.example.mynowinandroid.ui.following

import app.cash.turbine.test
import com.example.mynowinandroid.data.model.Topic
import com.example.mynowinandroid.testutil.TestDispatcherRule
import com.example.mynowinandroid.testutil.TestTopicsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class FollowingViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val topicsRepository = TestTopicsRepository()
    private lateinit var viewModel: FollowingViewModel

    @Before
    fun setUp() {
        viewModel = FollowingViewModel(topicsRepository = topicsRepository)
    }

    @Test
    fun uiState_whenInitialized_thenShowingLoading() = runTest {
        viewModel.uiState.test {
            assertEquals(FollowingUiState.Loading, awaitItem())
        }
    }

    @Test
    fun uiState_whenFollowedTopicsAreLoading_thenShowLoading() = runTest {
        viewModel.uiState.test {
            assertEquals(FollowingUiState.Loading, awaitItem())
            topicsRepository.setFollowedTopicIds(emptySet())
            cancel()
        }
    }

    @Test
    fun uiState_whenFollowingNewTopic_thenShowUpdatedTopics() = runTest {
        viewModel.uiState.test {
            awaitItem()
            topicsRepository.sendTopics(testInputTopics)
            topicsRepository.setFollowedTopicIds(setOf(testInputTopics[0].id))

            awaitItem()
            viewModel.followTopic(
                followedTopicId = testInputTopics[1].id,
                followed = !testInputTopics[1].followed,
            )

            assertEquals(FollowingUiState.Topics(topics = testOutputTopics), awaitItem())
            cancel()
        }
    }

    @Test
    fun uiState_whenUnfollowingTopics_thenShowUpdatedTopics() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(testOutputTopics)
                topicsRepository.setFollowedTopicIds(
                    setOf(testOutputTopics[0].id, testOutputTopics[1].id),
                )

                awaitItem()
                viewModel.followTopic(
                    followedTopicId = testOutputTopics[1].id,
                    followed = !testOutputTopics[1].followed,
                )

                assertEquals(
                    FollowingUiState.Topics(topics = testInputTopics),
                    awaitItem(),
                )
                cancel()
            }
    }
}

private const val TOPIC_1_NAME = "Android Studio"
private const val TOPIC_2_NAME = "Build"
private const val TOPIC_3_NAME = "Compose"
private const val TOPIC_DESC = "At vero eos et accusamus et iusto odio dignissimos ducimus qui."

private val testInputTopics = listOf(
    Topic(
        id = 0,
        name = TOPIC_1_NAME,
        description = TOPIC_DESC,
        followed = true,
    ),
    Topic(
        id = 1,
        name = TOPIC_2_NAME,
        description = TOPIC_DESC,
    ),
    Topic(
        id = 2,
        name = TOPIC_3_NAME,
        description = TOPIC_DESC,
    ),
)

private val testOutputTopics = listOf(
    Topic(
        id = 0,
        name = TOPIC_1_NAME,
        description = TOPIC_DESC,
        followed = true,
    ),
    Topic(
        id = 1,
        name = TOPIC_2_NAME,
        description = TOPIC_DESC,
        followed = true,
    ),
    Topic(
        id = 2,
        name = TOPIC_3_NAME,
        description = TOPIC_DESC,
    ),
)
