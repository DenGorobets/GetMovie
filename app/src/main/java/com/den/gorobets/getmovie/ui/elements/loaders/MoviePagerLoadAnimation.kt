package com.den.gorobets.getmovie.ui.elements.loaders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.den.gorobets.getmovie.extensions.calculateCurrentOffsetForPage
import com.den.gorobets.getmovie.ui.elements.shared.image.ShimmerLoading
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviePagerLoadAnimation(modifier: Modifier = Modifier) {

    val itemsCount = 10

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        itemsCount
    }
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(itemsCount)
    )

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
        beyondViewportPageCount = 5,
        pageSize = PageSize.Fill,
        flingBehavior = fling,
        key = null,
        pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
            pagerState, Orientation.Horizontal
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
                ShimmerLoading(
                    modifier = Modifier
                        .aspectRatio(10f / 16f)
                        .clip(RoundedCornerShape(16.dp))
                        .alpha(.5f)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                ShimmerLoading(
                    modifier = Modifier
                        .width(120.dp)
                        .padding(horizontal = 8.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .alpha(.5f)
                        .align(Alignment.CenterHorizontally),
                )
            }
        }
    )
}