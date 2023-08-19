package com.example.mynowinandroid.ui.following

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mynowinandroid.R
import com.example.mynowinandroid.data.model.Topic
import com.example.mynowinandroid.ui.NiaLoadingIndicator
import com.example.mynowinandroid.ui.NiaToolbar

@Composable
fun FollowingRoute(
    modifier: Modifier = Modifier,
    navigateToTopic: () -> Unit,
    viewModel: FollowingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    FollowingScreen(
        modifier = modifier,
        uiState = uiState,
        followTopic = viewModel::followTopic,
        navigateToTopic = navigateToTopic,
    )
}

@Composable
fun FollowingScreen(
    uiState: FollowingUiState,
    followTopic: (Int, Boolean) -> Unit,
    navigateToTopic: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NiaToolbar(titleRes = R.string.following)
        when (uiState) {
            FollowingUiState.Loading ->
                NiaLoadingIndicator(
                    modifier = modifier,
                    contentDesc = stringResource(id = R.string.following_loading),
                )

            is FollowingUiState.Topics -> FollowingWithTopicsScreen(
                uiState = uiState,
                onTopicClick = { navigateToTopic() },
                onFollowButtonClick = followTopic,
            )

            is FollowingUiState.Error -> FollowingErrorScreen()
        }
    }
}

@Composable
fun FollowingErrorScreen() {
    Text(text = stringResource(id = R.string.following_error_header))
}

@Composable
fun FollowingWithTopicsScreen(
    modifier: Modifier = Modifier,
    uiState: FollowingUiState.Topics,
    onTopicClick: () -> Unit,
    onFollowButtonClick: (Int, Boolean) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        uiState.topics.forEach {
            item {
                FollowingTopicCard(
                    topic = it,
                    onTopicClick = onTopicClick,
                    onFollowButtonClick = onFollowButtonClick,
                )
            }
        }
    }
}

@Composable
fun FollowingTopicCard(
    topic: Topic,
    onTopicClick: () -> Unit,
    onFollowButtonClick: (Int, Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.padding(
            start = 24.dp,
            end = 8.dp,
            bottom = 24.dp,
        ),
        verticalAlignment = Alignment.Top,
    ) {
        TopicIcon(
            modifier = Modifier.padding(end = 24.dp),
            onClick = onTopicClick,
        )
        Column(
            Modifier
                .wrapContentSize(Alignment.Center)
                .weight(1f)
                .clickable { onTopicClick() },
        ) {
            TopicTitle(topicName = topic.name)
            TopicDescription(topicDescription = topic.description)
        }
        FollowButton(topicId = topic.id, isFollowed = false, onClick = onFollowButtonClick)
    }
}

@Composable
fun FollowButton(
    topicId: Int,
    isFollowed: Boolean,
    onClick: (Int, Boolean) -> Unit,
) {
    IconToggleButton(checked = isFollowed, onCheckedChange = { onClick(topicId, !isFollowed) }) {
        if (isFollowed) {
            FollowedTopicIcon()
        } else {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription =
                stringResource(id = R.string.following_topic_card_follow_button_content_desc),
                modifier = Modifier.size(14.dp),
            )
        }
    }
}

@Composable
fun FollowedTopicIcon() {
    Box(
        modifier = Modifier
            .size(30.dp)
            .background(
                color = Color.Magenta.copy(alpha = 0.5f),
                shape = CircleShape,
            ),
    ) {
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription =
            stringResource(id = R.string.following_topic_card_unfollow_button_content_desc),
            modifier = Modifier.size(14.dp)
                .align(Alignment.Center),
        )
    }
}

@Composable
fun TopicTitle(topicName: String, modifier: Modifier = Modifier) {
    Text(
        topicName,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(top = 12.dp, bottom = 8.dp),
    )
}

@Composable
fun TopicDescription(topicDescription: String) {
    Text(
        text = topicDescription,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.wrapContentSize(Alignment.Center),
    )
}

@Composable
fun TopicIcon(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Filled.Android,
        tint = Color.Magenta,
        contentDescription = stringResource(id = R.string.following_topic_card_icon_content_desc),
        modifier = modifier
            .size(64.dp)
            .clickable { onClick() },
    )
}

@Preview(showBackground = true)
@Composable
fun FollowingErrorScreenPreview() {
    FollowingErrorScreen()
}

@Preview(showBackground = true)
@Composable
fun FollowTopicIconPreview() {
    FollowedTopicIcon()
}

@Preview(showBackground = true)
@Composable
fun FollowButtonPreview() {
    FollowButton(topicId = 1, isFollowed = false, onClick = { _, _ -> })
}

@Preview(showBackground = true)
@Composable
fun TopicCardPreview() {
    TopicIcon(onClick = {})
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Dark Mode",
)
@Composable
fun FollowingTopicCardPreview() {
    val topic = Topic(1, "Preview Topic", "This is preview topic description", true)
    FollowingTopicCard(
        topic = topic,
        onTopicClick = { /*TODO*/ },
        onFollowButtonClick = { _, _ -> },
    )
}

@Preview(showBackground = true)
@Composable
fun FollowingWithTopicScreenPreview() {
    FollowingWithTopicsScreen(
        uiState = FollowingUiState.Topics(emptyList()),
        onTopicClick = {},
        onFollowButtonClick = { _, _ -> },
    )
}

@Preview(showBackground = true)
@Composable
fun FollowingScreenPreview() {
    FollowingScreen(
        uiState = FollowingUiState.Loading,
        followTopic = { _, _ -> },
        navigateToTopic = { /*TODO*/ },
    )
}
