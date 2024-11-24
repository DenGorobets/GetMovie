package com.den.gorobets.getmovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LocalNavigatorScreenLifecycleProvider
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.ScaleTransition
import com.den.gorobets.getmovie.navigation.EmptyNavigatorScreenLifecycleProvider
import com.den.gorobets.getmovie.navigation.general_screen.MovieSplashScreen
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

    @OptIn(KoinExperimentalAPI::class, ExperimentalVoyagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            )
        )

        setContent {
            KoinAndroidContext {
                CompositionLocalProvider(
                    LocalNavigatorScreenLifecycleProvider provides EmptyNavigatorScreenLifecycleProvider
                ) {
                    Navigator(
                        screen = MovieSplashScreen,
                        disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false),
                    ) { navigator ->
                        ScaleTransition(
                            navigator = navigator,
                            disposeScreenAfterTransitionEnd = true,
                        ) { screen ->
                            screen.Content()
                        }
                    }
                }
            }
        }
    }
}