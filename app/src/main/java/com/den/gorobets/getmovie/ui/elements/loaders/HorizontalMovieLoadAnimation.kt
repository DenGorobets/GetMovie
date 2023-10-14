package com.den.gorobets.getmovie.ui.elements.loaders

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.den.gorobets.getmovie.ui.elements.shared.image.ShimmerLoading

@Composable
fun HorizontalMovieLoadAnimation(modifier: Modifier = Modifier) {

    val listState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .animateContentSize(spring())
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        ShimmerLoading(
            modifier = Modifier
                .fillMaxWidth(.4f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .alpha(.5f)
        )
        LazyRow(
            state = listState,
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(10) {
                Column(
                    modifier = Modifier
                        .width(120.dp)
                        .padding(8.dp)
                ) {
                    ShimmerLoading(
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(10f / 16f)
                            .clip(RoundedCornerShape(8.dp))
                            .alpha(.5f)
                    )
                }
            }
        }
    }
}