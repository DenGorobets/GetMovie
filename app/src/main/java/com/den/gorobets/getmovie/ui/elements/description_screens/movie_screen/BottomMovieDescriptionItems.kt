package com.den.gorobets.getmovie.ui.elements.description_screens.movie_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.extensions.openWebSite
import com.den.gorobets.getmovie.extensions.replaceOnDot
import com.den.gorobets.getmovie.navigation.description_screen.MovieDescriptionScreen
import com.den.gorobets.getmovie.navigation.list_screen.DiscoverMovieListScreen
import com.den.gorobets.getmovie.ui.elements.BigTitleText
import com.den.gorobets.getmovie.ui.elements.HorizontalMovieScrollerItem
import com.den.gorobets.getmovie.ui.elements.YouTubePlugin
import com.den.gorobets.getmovie.ui.elements.description_screens.ClickableTextDescriptionItem
import com.den.gorobets.getmovie.ui.elements.description_screens.RatingsMovieItem
import com.den.gorobets.getmovie.ui.elements.description_screens.TextDescription
import com.den.gorobets.getmovie.ui.elements.description_screens.TextDescriptionItem
import com.den.gorobets.getmovie.utils.data.DescriptionBottomPartData

@Composable
fun BottomMovieDescriptionItems(
    navigator: Navigator,
    data: DescriptionBottomPartData,
    themeTextColors: List<Color>,
) {

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RatingsMovieItem(
                data.correctRuntime,
                stringResource(R.string.text_runtime),
                textColor = themeTextColors
            )
            RatingsMovieItem(
                data.formatVote,
                stringResource(R.string.text_imdb_rating),
                textColor = themeTextColors
            )
        }
        TextDescription(
            headerText = stringResource(R.string.text_released_date),
            description = (data.releaseDate).replaceOnDot(),
            textColor = themeTextColors
        )
        TextDescriptionItem(
            headerText = "${stringResource(R.string.text_genre)}: ",
            descriptionText = data.filmGenre,
            textColor = themeTextColors
        )
        TextDescriptionItem(
            headerText = "${stringResource(R.string.text_country)}: ",
            descriptionText = data.filmingCountry,
            textColor = themeTextColors
        )
        ClickableTextDescriptionItem(
            stringResource(R.string.text_director),
            data.directorsCast,
            false,
            themeTextColors,
            navigator
        )
        ClickableTextDescriptionItem(
            stringResource(R.string.text_producer),
            data.producersCast,
            false,
            themeTextColors,
            navigator
        )
        HorizontalMovieScrollerItem(
            modifier = Modifier,
            label = stringResource(R.string.text_similar),
            data = data.similar,
            textColor = themeTextColors,
            moreButton = {
                navigator.push(DiscoverMovieListScreen)
            },
            navigationId = { _, index ->
                navigator.replace(MovieDescriptionScreen(index))
            }
        )
        ClickableTextDescriptionItem(
            stringResource(R.string.text_actors),
            data.actorsCast,
            true,
            themeTextColors,
            navigator
        )
        TextDescription(
            headerText = stringResource(R.string.text_plot),
            description = data.overview,
            textColor = themeTextColors,
            largeHeader = true
        )
        YouTubePlugin(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            videoPath = data.trailerVideo,
            label = {
                BigTitleText(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp, bottom = 4.dp),
                    label = stringResource(R.string.text_trailer),
                    textColor = themeTextColors[0]
                )
            }
        )
        TextDescription(
            modifier = Modifier.clickable {
                context.openWebSite(
                    data.homepage
                )
            },
            headerText = stringResource(R.string.text_website),
            description = data.homepage,
            textColor = themeTextColors
        )
    }
}