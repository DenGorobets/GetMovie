package com.den.gorobets.getmovie.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.gorobets.getmovie.ui.elements.shared.image.ImageUrlPainter

@Composable
fun <T> HorizontalMovieScrollerItem(
    label: String,
    movieList: List<T>,
    itemContent: @Composable (T) -> Unit
) {

    if (movieList.isNotEmpty()) {

        val listState = rememberLazyListState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .animateContentSize(spring())
        ) {

            Spacer(modifier = Modifier.padding(4.dp))
            LabelText(label = label)
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                itemsIndexed(movieList) { _, index ->
                    itemContent(index)
                }
            }
        }
    }
}

@Composable
fun LabelText(modifier: Modifier = Modifier, label: String) {

    Text(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp, bottom = 4.dp),
        text = label,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MovieScrollerItem(
    poster: String,
    name: String,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .animateContentSize(spring())
                .clickable { onClick() },
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(0.dp)
        ) {
            ImageUrlPainter(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(10f / 16f)
                    .clip(RoundedCornerShape(8.dp)),
                image = poster
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            modifier = Modifier.fillMaxSize(),
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelSmall
        )
    }
}