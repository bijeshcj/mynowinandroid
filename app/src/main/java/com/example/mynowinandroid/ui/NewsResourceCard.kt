package com.example.mynowinandroid.ui

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.example.mynowinandroid.R
import com.example.mynowinandroid.data.news.NewsResource
import com.example.mynowinandroid.ui.theme.NiaTheme

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
fun NewsResourceTitle(newsResource: NewsResource) {
    TODO()
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
fun NewsResourceShortDescription(newsResource: NewsResource) {
    TODO()
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
            BookmarkButton(isBookMarked = false, onClick = { })
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

@Preview("Expanded Resource Card")
@Preview("Expanded Resource Card (dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExpandedResourcePreview() {
    NiaTheme {
        Surface {
            NewsResourceCardExpanded()
        }
    }
}
