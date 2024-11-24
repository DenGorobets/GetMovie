package com.den.gorobets.getmovie.ui.elements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubePlugin(
    modifier: Modifier = Modifier,
    videoPath: String,
    label: @Composable (() -> Unit)? = null
) {

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val view by remember { mutableStateOf(YouTubePlayerView(context = context)) }
    var setTime: Float by rememberSaveable { mutableFloatStateOf(0f) }

    if (videoPath.isNotEmpty()) {
        label?.invoke()
        AndroidView(
            modifier = modifier,
            factory = {
                val options: IFramePlayerOptions =
                    IFramePlayerOptions.Builder().controls(1).fullscreen(0).ivLoadPolicy(3)
                        .build()

                val listener = object : AbstractYouTubePlayerListener() {

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                        super.onCurrentSecond(youTubePlayer, second)
                        setTime = second
                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(videoPath, setTime)
                    }
                }

                view.enableAutomaticInitialization = false
                view.initialize(listener, options)
                view
            })
    }

    DisposableEffect(true) {
        lifecycle.addObserver(view)

        onDispose {
            lifecycle.removeObserver(view)
        }
    }
}