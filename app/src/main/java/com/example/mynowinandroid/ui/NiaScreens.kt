package com.example.mynowinandroid.ui

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NiaToolbar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    onSearchClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .padding(bottom = 32.dp),
    ) {
        IconButton(onClick = { onSearchClick() }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription =
                null,
            )
        }
        Text(
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
        IconButton(onClick = { onMenuClick() }) {
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
        }
    }
}

@Composable
fun NiaLoadingIndicator(modifier: Modifier = Modifier, contentDesc: String) {
    Box(
        modifier = modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.semantics { contentDescription = contentDesc },
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

object ClearRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f,
    )
}

@SuppressLint("ResourceType")
@Preview(showBackground = true)
@Composable
fun NiaLoadingIndicatorPreview() {
    NiaLoadingIndicator(modifier = Modifier, "Loading")
}

@SuppressLint("ResourceType")
@Preview(showBackground = true)
@Composable
fun NiaToolbarPreview() {
    NiaToolbar(titleRes = 1, onSearchClick = {}, onMenuClick = {})
}
