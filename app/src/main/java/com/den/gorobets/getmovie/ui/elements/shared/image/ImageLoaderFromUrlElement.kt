package com.den.gorobets.getmovie.ui.elements.shared.image

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.api.TMBD_IMAGE_BASE_URL
import java.lang.Float.min


@Composable
fun ImageUrlPainter(
    modifier: Modifier = Modifier,
    image: String,
    withAnimation: Boolean = true,
    isPerson: Boolean = false
) {

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(TMBD_IMAGE_BASE_URL + image)
//            .crossfade(true)
//            .crossfade(450)
            .build(),
    )

    val state = painter.state
    val transition by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f,
        label = stringResource(
            R.string.transition_animation
        )
    )
    val transitionAlpha by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Loading) 1f else 0f,
        label = stringResource(
            R.string.transition_alpha
        )
    )
    val matrix = ColorMatrix()
    matrix.setToSaturation(transition)

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        if (image != "") {
            ShimmerLoading(modifier.alpha(transitionAlpha))
            Image(
                modifier = if (withAnimation)
                    modifier
                        .scale(.8f + (.2f * transition))
                        .graphicsLayer { rotationX = (1f - transition) * 5f }
                        .alpha(min(1f, transition / .2f))
                else
                    modifier,
                colorFilter = ColorFilter.colorMatrix(matrix),
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.text_card_image)
            )
        } else {
            if (isPerson)
                DefaultImage(modifier = modifier, isPerson = true)
            else
                DefaultImage(modifier = modifier, isPerson = false)
        }
    }
}

@Composable
fun DefaultImage(modifier: Modifier = Modifier, isPerson: Boolean) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                MaterialTheme
                    .colorScheme.primary.copy(0.2f)
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
            painter = if (isPerson)
                painterResource(
                    if (!isSystemInDarkTheme())
                        R.drawable.person_white
                    else
                        R.drawable.person_black
                )
            else
                painterResource(
                    if (!isSystemInDarkTheme())
                        R.drawable.movie_white
                    else
                        R.drawable.movie_black
                ),
            contentDescription = stringResource(R.string.text_default_image),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ShimmerLoading(modifier: Modifier = Modifier) {

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition =
        rememberInfiniteTransition(label = stringResource(R.string.transition_shimmer_animation))
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = stringResource(R.string.translate_shimmer_animation)
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = brush)
    )
}