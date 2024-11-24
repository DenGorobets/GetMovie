package com.den.gorobets.getmovie.ui.elements.home_screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.now_playing.ResultNowPlayingMovie
import com.den.gorobets.getmovie.extensions.calculateCurrentOffsetForPage
import com.den.gorobets.getmovie.navigation.description_screen.MovieDescriptionScreen
import com.den.gorobets.getmovie.ui.elements.loaders.MoviePagerLoadAnimation
import com.den.gorobets.getmovie.ui.elements.shared.image.ImageUrlPainter
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.utils.ResponseEvents
import kotlinx.coroutines.delay
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
    val currentPage by remember { derivedStateOf { pagerState.currentPage } }

    LaunchedEffect(currentPage) {
        haptic.performHapticFeedback(HapticFeedbackType(HapticFeedbackConstants.LONG_PRESS))
    }

    LaunchedEffect(key1 = autoSwipe) {
        delay(5000)

        val target =
            if (currentPage + 1 != movies.size) currentPage + 1 else 0

        pagerState.animateScrollToPage(
            page = target,
            animationSpec = tween(
                easing = LinearEasing,
                durationMillis = 500
            )
        )
        autoSwipe = !autoSwipe
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
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            pagerState, Orientation.Horizontal
        ),
        beyondBoundsPageCount = 5,
        pageSize = PageSize.Fill,
        flingBehavior = fling,
        key = null,
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
                    }
                    .clickable { callback(movies[currentPage].id ?: 0) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageUrlPainter(
                    modifier = Modifier
                        .aspectRatio(10f / 16f)
                        .clip(RoundedCornerShape(16.dp)),
                    image = movies[page].posterPath.orEmpty(),
                    withAnimation = false
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    text = movies[page].title.orEmpty(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    )
}

@Composable
fun MoviePagerLoader(
    modifier: Modifier = Modifier,
    nowPlayingListItems: ResponseEvents<NowPlayingListMovieDTO>,
    navigator: Navigator,
    errorCallback: (String) -> Unit
) {

    when (nowPlayingListItems) {
        is ResponseEvents.Failure -> {
            val error = nowPlayingListItems.error
            errorCallback.invoke(error)
        }

        is ResponseEvents.Loading -> {
            MoviePagerLoadAnimation()
        }

        is ResponseEvents.Success -> {
            val data = nowPlayingListItems.result

            MoviePager(
                modifier = modifier,
                movies = data?.resultNowPlayingMovies ?: emptyList()
            ) {
                navigator.push(MovieDescriptionScreen(it))
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun MoviePagerPreview() {
    GetMovieTheme {
        MoviePager(
            movies = listOf(),
            callback = {}
        )
    }
}