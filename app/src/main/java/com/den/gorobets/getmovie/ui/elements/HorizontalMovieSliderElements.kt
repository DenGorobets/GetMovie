package com.den.gorobets.getmovie.ui.elements

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
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
    modifier: Modifier = Modifier,
    title: String,
    data: List<T>,
    itemContent: @Composable (T) -> Unit,
    addButton: @Composable (() -> Unit)? = null
) {

    if (data.isNotEmpty()) {

        val listState = rememberLazyListState()

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LabelText(label = title)
                addButton?.invoke()
            }
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                itemsIndexed(data) { _, index ->
                    itemContent.invoke(index)
                }
            }
        }
    }
}

@Composable
fun LabelText(label: String) {

    Text(
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
                .clickable { onClick.invoke() },
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(0.dp)
        ) {
            ImageUrlPainter(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(10f / 16f)
                    .clip(RoundedCornerShape(8.dp)),
                image = poster,
                withAnimation = false
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