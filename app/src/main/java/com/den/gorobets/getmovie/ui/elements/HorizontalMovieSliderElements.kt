package com.den.gorobets.getmovie.ui.elements

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.ui.elements.shared.image.ImageUrlPainter
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.utils.data.MovieSeriesItem

@Composable
fun HorizontalMovieScrollerItem(
    modifier: Modifier = Modifier,
    label: String,
    data: List<MovieSeriesItem>,
    textColor: List<Color> = listOf(
        MaterialTheme.colorScheme.onBackground,
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    ),
    navigationId: ((Int, Int) -> Unit)? = null,
    moreButton: (() -> Unit)? = null
) {

    val listState = rememberLazyListState()

    if (!data.isNullOrEmpty())
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            BigTitleText(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                label = label,
                textColor = textColor[0]
            )
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(items = data) { count, (poster, title, imdbId, isSeries, randomId) ->
                    MovieScrollerItem(
                        poster,
                        title,
                        textColor = textColor[0]
                    ) {
                        navigationId?.invoke(count, imdbId)
                    }
                }
                item {
                    MoreLazyRowButtonItem(textColor = textColor[0]) {
                        moreButton?.invoke()
                    }
                }
            }
        }
}

@Composable
fun BigTitleText(modifier: Modifier = Modifier, textColor: Color, label: String) {

    Text(
        modifier = modifier,
        text = label,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = textColor
    )
}

@Composable
fun MovieScrollerItem(
    poster: String,
    name: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
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
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

@Composable
fun MoreLazyRowButtonItem(
    textColor: Color,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                MaterialTheme
                    .colorScheme.primary.copy(0.1f)
            )
            .clickable { onClick.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            text = stringResource(R.string.tap_to_see_all),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

@Preview
@Composable
fun MoreLazyRowButtonItemPreview() {
    GetMovieTheme {
        MoreLazyRowButtonItem(textColor = MaterialTheme.colorScheme.onBackground) {}
    }
}