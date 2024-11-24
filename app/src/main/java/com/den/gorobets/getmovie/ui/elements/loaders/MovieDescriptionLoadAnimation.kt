package com.den.gorobets.getmovie.ui.elements.loaders

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.den.gorobets.getmovie.ui.elements.shared.image.ShimmerLoading

@Composable
fun MovieDescriptionLoadAnimation(
    paddingValues: PaddingValues
) {

    val config = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val ratioImageBackground = if (config) 1f else 1f / 0.7f
    val cardWeight = if (config) 0.95f else 0.6f

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(ratioImageBackground)
                .fillMaxSize()
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .alpha(.5f),
                verticalAlignment = Alignment.Bottom
            ) {
                ShimmerLoading(
                    modifier = Modifier
                        .aspectRatio(10f / 16f)
                        .fillMaxSize()
                        .weight(cardWeight)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.size(18.dp))
                Column(modifier = Modifier.weight(1f)) {
                    ShimmerLoading(
                        modifier = Modifier
                            .size(100.dp, 30.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    ShimmerLoading(
                        modifier = Modifier
                            .size(150.dp, 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .alpha(.5f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ShimmerLoading(
                    modifier = Modifier
                        .size(60.dp, 20.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.size(8.dp))
                ShimmerLoading(
                    modifier = Modifier
                        .size(120.dp, 14.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ShimmerLoading(
                    modifier = Modifier
                        .size(120.dp, 20.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.size(8.dp))
                ShimmerLoading(
                    modifier = Modifier
                        .size(80.dp, 14.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .alpha(.5f)
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            ShimmerLoading(
                modifier = Modifier
                    .size(120.dp, 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.size(12.dp))
            ShimmerLoading(
                modifier = Modifier
                    .size(160.dp, 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.size(12.dp))
            ShimmerLoading(
                modifier = Modifier
                    .size(190.dp, 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.size(12.dp))
            ShimmerLoading(
                modifier = Modifier
                    .size(160.dp, 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.size(12.dp))
            ShimmerLoading(
                modifier = Modifier
                    .size(180.dp, 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.size(12.dp))
            ShimmerLoading(
                modifier = Modifier
                    .size(200.dp, 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
        HorizontalMovieLoadAnimation()
    }
    Spacer(Modifier.padding(bottom = paddingValues.calculateBottomPadding()))
}