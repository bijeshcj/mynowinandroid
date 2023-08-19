package com.example.mynowinandroid.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.mynowinandroid.R
import com.example.mynowinandroid.data.model.NewsResource
import com.example.mynowinandroid.data.network.NetworkAuthor
import com.example.mynowinandroid.data.network.NetworkEpisode
import com.example.mynowinandroid.data.network.NetworkTopic
import com.example.mynowinandroid.data.network.asEntity
import com.example.mynowinandroid.ui.theme.NiaTheme
import com.google.samples.apps.nowinandroid.data.fake.FakeDataSource

@Composable
fun NewsResourceCardExpanded(
    newsResource: NewsResource,
    isBookMarked: Boolean,
    onToggleBookmark: () -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            NewsResourceTitle(
                newsResource.entity.title,
                modifier = Modifier.fillMaxWidth(.8f),
            )
            Spacer(modifier = Modifier.weight(1f))
            BookmarkButton(isBookMarked = isBookMarked, onClick = onToggleBookmark)
        }
    }
}

@Composable
fun BookmarkButton(
    isBookMarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var clickActionLabel = stringResource(
        if (isBookMarked) R.string.unbookmark else R.string.bookmark,
    )

    IconToggleButton(
        checked = isBookMarked,
        onCheckedChange = { onClick() },
        modifier = modifier.semantics {
            this.onClick(label = clickActionLabel, action = null)
        },
    ) {
        Icon(
            imageVector = if (isBookMarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
            contentDescription = null,
        )
    }
}

@Composable
fun NewsResourceAuthors(newsResource: NewsResource) {
    TODO()
}

@Composable
fun NewsResourceHeaderImage(newsResource: NewsResource) {
    TODO()
}

@Composable
fun NewsResourceTitle(
    newsResourceTitle: String,
    modifier: Modifier = Modifier,
) {
    Text(
        newsResourceTitle,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier,
    )
}

@Composable
fun NewsResourceDate(newsResource: NewsResource) {
    TODO()
}

@Composable
fun NewsResourceLink(newsResource: NewsResource) {
    TODO()
}

@Composable
fun NewsResourceShortDescription(newsResourceShortDescription: String) {
    Text(text = newsResourceShortDescription, style = MaterialTheme.typography.bodyMedium)
}

@Composable
fun NewsResourceTopics(newsResource: NewsResource) {
    TODO()
}

@Composable
fun NewsResourceCardExpanded() {
    TODO()
}

@Preview("Bookmark Button Bookmarked")
@Composable
fun BookmarkButtonPreview() {
    NiaTheme {
        Surface {
            BookmarkButton(isBookMarked = false, onClick = { /*TODO*/ })
        }
    }
}

@Preview("Bookmark Button Bookmarked")
@Composable
fun BookmarkButtonBookmarkedPreview() {
    NiaTheme {
        Surface {
            BookmarkButton(isBookMarked = true, onClick = { })
        }
    }
}

@Composable
fun ExpandedNewsResourcePreview(
    @PreviewParameter(NewsResourcePreviewParameterProvider::class) newsResource: NewsResource,
) {
    NiaTheme {
        Surface {
            NewsResourceCardExpanded(newsResource, true, {})
        }
    }
}

class NewsResourcePreviewParameterProvider : PreviewParameterProvider<NewsResource> {
    override val values: Sequence<NewsResource> = sequenceOf(
        NewsResource(
            FakeDataSource.sampleResource.asEntity(),
            NetworkEpisode(
                id = 1,
                name = "Now in Android 40",
                alternateAudio = null,
                alternateVideo = null,
                publishDate = FakeDataSource.sampleResource.publishDate,
            ).asEntity(),
            listOf(
                NetworkAuthor(
                    id = 1,
                    name = "Android",
                    imageUrl = "",
                ).asEntity(),
            ),
            listOf(
                NetworkTopic(
                    id = 3,
                    name = "Performance",
                    description = "",
                ).asEntity(),
            ),
        ),
    )
}
