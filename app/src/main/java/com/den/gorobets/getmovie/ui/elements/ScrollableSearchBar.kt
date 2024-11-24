package com.den.gorobets.getmovie.ui.elements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.den.gorobets.getmovie.ui.elements.shared.SearchField

@Composable
fun ScrollableSearchBar(
    modifier: Modifier = Modifier,
    scrollUpState: Boolean,
    searchFieldText: MutableState<String>,
) {

    val position by animateFloatAsState(if (scrollUpState) -350f else 0f, label = "")

    SearchField(
        modifier = modifier.graphicsLayer { translationY = (position) },
        searching = { searchFieldText.value = it }
    )
}