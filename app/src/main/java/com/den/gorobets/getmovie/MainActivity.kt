package com.den.gorobets.getmovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.navigation.MovieSplashScreen

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(screen = MovieSplashScreen)
//            { navigator ->
//                ScaleTransition(navigator)
//            }
        }
    }
}