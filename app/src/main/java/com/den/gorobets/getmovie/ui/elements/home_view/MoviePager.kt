package com.den.gorobets.getmovie.ui.elements.home_view

import android.view.HapticFeedbackConstants
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.den.gorobets.getmovie.dto.now_playing.ResultNowPlayingMovie
import com.den.gorobets.getmovie.extensions.calculateCurrentOffsetForPage
import com.den.gorobets.getmovie.ui.elements.shared.image.ImageUrlPainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviePager(
    modifier: Modifier = Modifier,
    movies: List<ResultNowPlayingMovie>,
    callback: (Int) -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        movies.size
    }
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(movies.size)
    )
    var autoSwipe by remember { mutableStateOf(false) }


    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPageOffsetFraction }.collect { page ->
            if (page == 0f || page == 1f) {
                haptic.performHapticFeedback(HapticFeedbackType(HapticFeedbackConstants.LONG_PRESS))
            }
        }
    }

    LaunchedEffect(key1 = autoSwipe) {

        launch {
            delay(5000)

            val target =
                if (pagerState.currentPage + 1 != movies.size) pagerState.currentPage + 1 else 0

            pagerState.animateScrollToPage(
                page = target,
                animationSpec = tween(
                    easing = LinearEasing,
                    durationMillis = 500
                )
            )
            autoSwipe = !autoSwipe
        }
    }

    HorizontalPager(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(),
        state = pagerState,
        pageSpacing = 0.dp,
        verticalAlignment = Alignment.CenterVertically,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(
            horizontal = (LocalConfiguration.current.screenWidthDp / 2 - 100).dp
        ),
        beyondBoundsPageCount = 5,
        pageSize = PageSize.Fill,
        flingBehavior = fling,
        key = null,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            Orientation.Horizontal
        ),
        pageContent = { page ->
            Column(
                Modifier
                    .graphicsLayer {
                        val pageOffset =
                            pagerState.calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.6f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageUrlPainter(
                    modifier = Modifier
                        .aspectRatio(10f / 16f)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { callback(pagerState.currentPage) },
                    image = movies[page].posterPath ?: "",
                    withAnimation = false
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    text = movies[page].title ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MoviePagerPreview() {
    MoviePager(
        movies = listOf(),
        callback = {}
    )
}