package com.example.mynowinandroid.ui.foryou

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.mynowinandroid.data.news.Topic
import org.junit.Rule
import org.junit.Test
import com.example.mynowinandroid.R

class ForYouScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun circularProgressIndicator_whenScreenIsLoading_exists() {
        composeTestRule.setContent {
            ForYouScreen(
                uiState = ForYouFeedUiState.Loading,
                onTopicCheckedChanged = { _, _ -> },
                saveFollowedTopics = { },
            )
        }
    }

    @Test
    fun topicSelector_whenNoTopicsSelected_showsTopicChipsAndDisabledDoneButton() {
        composeTestRule.setContent {
            ForYouScreen(
                uiState = ForYouFeedUiState.PopulatedFeed.FeedWithTopicsSelection(
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
                onTopicCheckedChanged = { _, _ -> },
                saveFollowedTopics = {},
            )
        }

        composeTestRule.onNodeWithText("HEADLINES")
            .assertIsDisplayed()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText("UI")
            .assertIsDisplayed()
            // .assertIsOff()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText("TOOLS")
            .assertIsDisplayed()
            // .assertIsOff()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText(composeTestRule.activity.resources.getString(R.string.done))
            .assertIsDisplayed()
            .assertIsNotEnabled()
            .assertHasClickAction()
    }
}
