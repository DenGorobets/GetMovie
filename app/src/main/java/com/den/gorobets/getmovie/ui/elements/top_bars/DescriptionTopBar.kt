package com.den.gorobets.getmovie.ui.elements.top_bars

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.den.gorobets.getmovie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    isItemStarred: MutableState<Boolean>,
    navigationButton: () -> Unit,
    shareButton: () -> Unit
) {

    TopAppBar(
        modifier = modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.surface, Color.Transparent
                )
            )
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = { navigationButton.invoke() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        actions = {
            IconButton(onClick = { shareButton.invoke() }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(R.string.share_button)
                )
            }
            IconButton(onClick = { isItemStarred.value = !isItemStarred.value }) {
                Crossfade(
                    targetState = isItemStarred.value,
                    label = stringResource(R.string.animated_icon_change)
                ) { isChecked ->
                    if (isChecked)
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = stringResource(R.string.start_save_icon_state)
                        )
                    else
                        Icon(
                            imageVector = Icons.Default.StarOutline,
                            contentDescription = stringResource(R.string.end_save_icon_state)
                        )
                }
            }
        })
}