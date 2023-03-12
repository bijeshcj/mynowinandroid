package com.example.mynowinandroid.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NiaApp()
        }
    }

    @Test
    fun firstScreenIsForYou() {
        composeTestRule.forYouDestinationTopMatcher()
            .assertExists("Could not find FOR YOU text on first screen after app open")
    }

    @Test
    fun topLevelDestinationsDoNotShowUpArrow() {
        composeTestRule.onNodeWithContentDescription("Navigate up")
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("Episodes").performClick()
        composeTestRule.onNodeWithContentDescription("Navigate up")
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("Saved").performClick()
        composeTestRule.onNodeWithContentDescription("Navigate up")
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("Topics").performClick()
        composeTestRule.onNodeWithContentDescription("Navigate up")
            .assertDoesNotExist()
    }

    @Test(expected = NoActivityResumedException::class)
    fun backFromHomeDestinationQuitsApp() {
        composeTestRule.onNodeWithText("Episodes").performClick()
        composeTestRule.onNodeWithText("For you").performClick()
        Espresso.pressBack()
    }

    @Test
    fun backFromDestinationReturnsToForYou() {
        composeTestRule.onNodeWithText("Episodes").performClick()
        composeTestRule.onNodeWithText("Topics").performClick()
        Espresso.pressBack()
        composeTestRule.forYouDestinationTopMatcher().assertExists()
    }

    private fun ComposeTestRule.forYouDestinationTopMatcher() = onNodeWithText("FOR YOU")

    private fun ComposeTestRule.topicsDestinationTopMatcher() = onNodeWithText("TOPICS")
}
