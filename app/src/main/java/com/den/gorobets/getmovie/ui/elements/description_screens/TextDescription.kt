package com.den.gorobets.getmovie.ui.elements.description_screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.den.gorobets.getmovie.ui.elements.BigTitleText

@Composable
fun TextDescription(
    modifier: Modifier = Modifier,
    headerText: String,
    description: String,
    textColor: List<Color>,
    largeHeader: Boolean = false
) {

    if (description.isNotEmpty()) {
        if (largeHeader) {
            BigTitleText(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 4.dp),
                label = headerText,
                textColor = textColor[0]
            )
            TextDescriptionItem(
                modifier = modifier,
                descriptionText = listOf(description),
                textColor = textColor
            )
        } else {
            TextDescriptionItem(
                modifier = modifier,
                headerText = "$headerText: ",
                descriptionText = listOf(description),
                textColor = textColor
            )
        }
    }
}