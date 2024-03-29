package com.example.mynowinandroid.ui.foryou

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.mynowinandroid.data.model.Topic
import com.example.mynowinandroid.testutil.TestDispatcherRule
import com.example.mynowinandroid.testutil.TestNewsRepository
import com.example.mynowinandroid.testutil.TestTopicsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class ForYouViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val topicsRepository = TestTopicsRepository()
    private val newsRepository = TestNewsRepository()
    private lateinit var viewModel: ForYouViewModel

    @Before
    fun setup() {
        viewModel = ForYouViewModel(
            topicsRepository = topicsRepository,
            newsRepository = newsRepository,
            savedStateHandle = SavedStateHandle(),
        )
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        viewModel.uiState.test {
            assertEquals(ForYouFeedUiState.Loading, awaitItem())
        }
    }

    @Test
    fun stateIsLoadingWhenFollowedTopicsAreLoading() = runTest {
        viewModel.uiState.test {
            assertEquals(ForYouFeedUiState.Loading, awaitItem())
            topicsRepository.sendTopics(sampleTopics)
            cancel()
        }
    }

    @Test
    fun stateIsLoadingWhenNewsResourcesAreLoading() = runTest {
        viewModel.uiState.test {
            awaitItem()
            topicsRepository.sendTopics(sampleTopics)
            topicsRepository.setFollowedTopicIds(emptySet())

            cancel()
        }
    }

    @Test
    fun stateIsTopicSelectionAfterLoadingEmptyFollowedTopics() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(emptySet())
                newsRepository.sendNewsResources(emptyList())

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithTopicsSelection(
                        selectedTopics = listOf(
                            Topic(
                                id = 0,
                                name = "Headlines",
                                description = "",
                            ) to false,
                            Topic(
                                id = 1,
                                name = "UI",
                                description = "",
                            ) to false,
                            Topic(
                                id = 2,
                                name = "Tools",
                                description = "",
                            ) to false,
                        ),
                        feed = emptyList(),
                    ),
                    awaitItem(),
                )
                cancel()
            }
    }

    @Test
    fun stateIsWithoutTopicSelectionAfterLoadingFollowedTopics() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(setOf(0, 1))
                newsRepository.sendNewsResources(emptyList())

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicsSelection(
                        feed = emptyList(),
                    ),
                    awaitItem(),
                )
                cancel()
            }
    }

    @Test
    fun topicSelectionUpdatesAfterSelectingTopic() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(emptySet())
                newsRepository.sendNewsResources(emptyList())

                awaitItem()
                viewModel.updateTopicSelection(1, isChecked = true)

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithTopicsSelection(
                        selectedTopics = listOf(
                            Topic(
                                id = 0,
                                name = "Headlines",
                                description = "",
                            ) to false,
                            Topic(
                                id = 1,
                                name = "UI",
                                description = "",
                            ) to true,
                            Topic(
                                id = 2,
                                name = "Tools",
                                description = "",
                            ) to false,
                        ),
                        feed = emptyList(),
                    ),
                    awaitItem(),
                )
                cancel()
            }
    }

    @Test
    fun topicSelectionUpdatesAfterUnselectingTopic() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(emptySet())
                newsRepository.sendNewsResources(emptyList())

                awaitItem()
                viewModel.updateTopicSelection(1, isChecked = true)

                awaitItem()
                viewModel.updateTopicSelection(1, isChecked = false)

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithTopicsSelection(
                        selectedTopics = listOf(
                            Topic(
                                id = 0,
                                name = "Headlines",
                                description = "",
                            ) to false,
                            Topic(
                                id = 1,
                                name = "UI",
                                description = "",
                            ) to false,
                            Topic(
                                id = 2,
                                name = "Tools",
                                description = "",
                            ) to false,
                        ),
                        feed = emptyList(),
                    ),
                    awaitItem(),
                )
                cancel()
            }
    }

    @Test
    fun topicSelectionUpdatesAfterSavingTopics() = runTest {
        viewModel.uiState
            .test {
                awaitItem()
                topicsRepository.sendTopics(sampleTopics)
                topicsRepository.setFollowedTopicIds(emptySet())
                newsRepository.sendNewsResources(emptyList())

                awaitItem()
                viewModel.updateTopicSelection(1, isChecked = true)

                awaitItem()
                viewModel.saveFollowedTopics()

                assertEquals(
                    ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicsSelection(
                        feed = emptyList(),
                    ),
                    awaitItem(),
                )
                Assert.assertEquals(setOf(1), topicsRepository.getCurrentFollowedtopics())
                cancel()
            }
    }

    private val sampleTopics = listOf(
        Topic(
            id = 0,
            name = "Headlines",
            description = "",
        ),
        Topic(
            id = 1,
            name = "UI",
            description = "",
        ),
        Topic(
            id = 2,
            name = "Tools",
            description = "",
        ),
    )
}
