package com.den.gorobets.getmovie.ui.elements.description_screens.movie_screen

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.gorobets.getmovie.extensions.drawableToBitmap
import com.den.gorobets.getmovie.ui.elements.shared.image.ImageUrlPainter
import com.den.gorobets.getmovie.utils.data.DescriptionTopPartData


@Composable
fun TopMovieDescriptionItems(
    modifier: Modifier = Modifier,
    data: DescriptionTopPartData,
    isPerson: Boolean = false,
    themeBackColor: Color,
    themeTextColors: List<Color>,
    bitmapFromPoster: MutableState<Bitmap?>
) {

    val currentScreenOrientation = LocalConfiguration.current.orientation
    val isPortrait by remember {
        mutableStateOf(currentScreenOrientation == Configuration.ORIENTATION_PORTRAIT)
    }
    val ratioImageBackground by remember { derivedStateOf { if (isPortrait) 1f else 1f / 0.7f } }
    val cardWeight by remember { derivedStateOf { if (isPortrait) 0.95f else 0.6f } }

    val backgroundImageModifier = remember {
        Modifier
            .aspectRatio(ratioImageBackground)
            .fillMaxSize()
            .blur(10.dp)
            .alpha(0.45f)
    }

    val themeGradient = remember(themeBackColor) {
        Brush.verticalGradient(
            colors = listOf(
                Color.Transparent,
                themeBackColor.copy(alpha = 0.5f),
                themeBackColor
            )
        )
    }

    val onDrawableReceived = remember {
        { drawable: Drawable ->
            bitmapFromPoster.value = drawable.drawableToBitmap()
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {

        ImageUrlPainter(
            modifier = backgroundImageModifier,
            withAnimation = false,
            image = data.backdrop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind { drawRect(themeGradient) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Card(
                    modifier = Modifier
                        .aspectRatio(10f / 16f)
                        .fillMaxSize()
                        .weight(cardWeight),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme
                            .colorScheme
                            .primary
                            .copy(0.2f)
                    )
                ) {
                    ImageUrlPainter(
                        modifier = Modifier.fillMaxSize(),
                        withAnimation = false,
                        image = data.poster,
                        isPerson = isPerson,
                        returnDrawable = onDrawableReceived
                    )
                }
                Spacer(modifier = Modifier.size(18.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = data.title,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        lineHeight = 30.sp,
                        style = MaterialTheme.typography.labelLarge,
                        color = themeTextColors[0]
                    )
                    if (data.checkTaglineEmpty())
                        Text(
                            color = themeTextColors[1],
                            text = data.taglineWithQuotes,
                            modifier = Modifier.padding(top = 8.dp),
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 16.sp
                        )
                }
            }
        }
    }
}