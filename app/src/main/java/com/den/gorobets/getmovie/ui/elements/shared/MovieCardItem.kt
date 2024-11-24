package com.den.gorobets.getmovie.ui.elements.shared

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.extensions.displayAdditionalData
import com.den.gorobets.getmovie.extensions.selectImageResource
import com.den.gorobets.getmovie.extensions.selectTitleResource
import com.den.gorobets.getmovie.extensions.selectTypeResource
import com.den.gorobets.getmovie.extensions.selectYearResource
import com.den.gorobets.getmovie.ui.elements.shared.image.ImageUrlPainter
import com.den.gorobets.getmovie.dto.search.Result

@Composable
fun MovieCardItem(
    movieData: Result,
    onClickItem: () -> Unit
) {

    val resultType =
        movieData.mediaType == stringResource(R.string.text_check_image_person)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme
                .colorScheme.primary.copy(0.2f)
        ),
        modifier = Modifier
            .size(300.dp)
            .padding(8.dp)
            .animateContentSize(spring())
            .clickable { onClickItem() }
    ) {
        Box {
            ImageUrlPainter(
                modifier = Modifier.fillMaxSize(),
                image = movieData.selectImageResource() ?: "",
                isPerson = resultType,
                withAnimation = false
            )
            Column(
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(modifier = Modifier.weight(1f)) {}
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background.copy(0.9f),
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        )
                        .animateContentSize(spring()),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = movieData.selectTitleResource() ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                        text = (movieData.selectYearResource() to movieData.selectTypeResource()).displayAdditionalData(),
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}