package com.den.gorobets.getmovie.ui.views

import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.navigation.SearchScreen
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme

@Composable
fun MovieSplashScreenView() {

    val navigator = LocalNavigator.currentOrThrow
    var startAnimation by remember { mutableStateOf(false) }
    val animAlpha = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = FloatTweenSpec(3000, easing = EaseInBounce),
        finishedListener = {
            navigator.replaceAll(SearchScreen) //TODO: replace with HomeScreen
        }, label = stringResource(R.string.splash_animation)
    )

    LaunchedEffect(true) {
        startAnimation = true
    }

    SplashScreen(alpha = animAlpha.value)
}

@Composable
fun SplashScreen(alpha: Float) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(3.dp),
            painter = painterResource(id = R.drawable.splash),
            contentDescription = stringResource(R.string.splash_background),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .size(240.dp)
                .clip(shape = CircleShape)
                .background(
                    MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
                )
        )
        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(shape = CircleShape)
                .alpha(alpha),
            painter = painterResource(
                id = if (isSystemInDarkTheme())
                    R.drawable.white_movies_24
                else
                    R.drawable.black_movies_24
            ),
            contentDescription = stringResource(R.string.splash_logo),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MovieSplashScreenPreview() {
    GetMovieTheme {
        MovieSplashScreenView()
    }
}