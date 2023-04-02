package com.example.mynowinandroid.ui.foryou

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mynowinandroid.R
import com.example.mynowinandroid.data.news.NewsResource
import com.example.mynowinandroid.data.news.Topic
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ForYouRoute(
    modifier: Modifier = Modifier,
    viewModel: ForYouViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    ForYouScreen(
        modifier = modifier,
        uiState = uiState,
        onTopicCheckedChanged = viewModel::updateTopicSelection,
        saveFollowedTopics = viewModel::saveFollowedTopics,
    )
}

@Composable
fun ForYouScreen(
    uiState: ForYouFeedUiState,
    onTopicCheckedChanged: (Int, Boolean) -> Unit,
    saveFollowedTopics: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            is ForYouFeedUiState.Loading -> {
                val forYouLoading = stringResource(id = R.string.for_you_loading)

                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .semantics {
                            contentDescription = forYouLoading
                        },
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            is ForYouFeedUiState.PopulatedFeed -> {
                LazyColumn {
                    when (uiState) {
                        is ForYouFeedUiState.PopulatedFeed.FeedWithTopicsSelection -> {
                            TopicSelection(uiState, onTopicCheckedChanged, saveFollowedTopics)
                        }
                        is ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicsSelection -> {
                        }
                    }
                    items(uiState.feed) { _: NewsResource ->
                    }
                }
            }
        }
    }
}

private fun LazyListScope.TopicSelection(
    uiState: ForYouFeedUiState.PopulatedFeed.FeedWithTopicsSelection,
    onTopicCheckedChanged: (Int, Boolean) -> Unit,
    saveFollowTopics: () -> Unit,
) {
    item {
        FlowRow(
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp,
            modifier = Modifier.padding(horizontal = 40.dp),
        ) {
            uiState.selectedTopics.forEach { (topic, isSelected) ->
                key(topic.id) {
                    OutlinedButton(
                        onClick = { onTopicCheckedChanged(topic.id, !isSelected) },
                        shape = RoundedCornerShape(50),
                        colors = if (isSelected) {
                            ButtonDefaults.buttonColors()
                        } else {
                            ButtonDefaults.outlinedButtonColors()
                        },
                    ) {
                        Text(text = topic.name.uppercase())
                    }
                }
            }
        }
    }
    item {
        Button(
            onClick = { saveFollowTopics },
            enabled = uiState.canSaveSelectedTopics,
            modifier = Modifier.padding(horizontal = 40.dp).fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.done))
        }
    }
}

@Preview
@Composable
fun ForYouScreenLoading() {
    ForYouScreen(
        uiState = ForYouFeedUiState.Loading,
        onTopicCheckedChanged = { _, _ -> },
        saveFollowedTopics = { /*TODO*/ },
    )
}

@Preview
@Composable
fun ForYouScreenTopicSelection() {
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
                ) to true,
                Topic(
                    id = 2,
                    name = "Tools",
                    description = "",
                ) to false,
            ),
            feed = emptyList(),
        ),
        onTopicCheckedChanged = { _, _ -> },
        saveFollowedTopics = { },
    )
}

@Preview
@Composable
fun PopulatedFeed() {
    ForYouScreen(
        uiState = ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicsSelection(
            feed = emptyList(),
        ),
        onTopicCheckedChanged = { _, _ -> },
        saveFollowedTopics = { /*TODO*/ },
    )
}