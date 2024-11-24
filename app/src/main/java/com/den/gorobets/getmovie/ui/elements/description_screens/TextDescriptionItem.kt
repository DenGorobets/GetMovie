package com.den.gorobets.getmovie.ui.elements.description_screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextDescriptionItem(
    modifier: Modifier = Modifier,
    headerText: String = "",
    descriptionText: List<String>,
    textColor: List<Color?>
) {

    val defaultHeaderColor = MaterialTheme.colorScheme.onBackground
    val defaultBodyColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f)

    val headerTextColor = remember(textColor[0], defaultHeaderColor) {
        textColor[0] ?: defaultHeaderColor
    }

    val bodyTextColor = remember(textColor[1], defaultBodyColor) {
        textColor[1] ?: defaultBodyColor
    }

    val textModifier = remember(modifier) {
        modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    }

    val descriptionString = remember(descriptionText) {
        descriptionText.joinToString(", ")
    }

    val annotatedString = remember(headerText, headerTextColor, bodyTextColor, descriptionString) {
        buildAnnotatedString {
            if (headerText.isNotEmpty()) {
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = headerTextColor
                    )
                ) {
                    append(headerText)
                }
            }
            withStyle(
                SpanStyle(
                    color = bodyTextColor
                )
            ) {
                append(descriptionString)
            }
        }
    }

    if (descriptionText.isNotEmpty()) {
        Text(
            text = annotatedString,
            modifier = textModifier,
            fontSize = 16.sp
        )
    }
}