package com.den.gorobets.getmovie.ui.elements.description_screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.navigation.description_screen.PersonDescriptionScreen
import com.den.gorobets.getmovie.ui.elements.BigTitleText

@Composable
fun ClickableTextDescriptionItem(
    stringResource: String = "",
    personsMap: Map<String, Int>,
    largeHeader: Boolean,
    textColor: List<Color>,
    navigator: Navigator,
    initialVisibleCount: Int = 7
) {
    var isExpanded by remember { mutableStateOf(false) }

    if (personsMap.isNotEmpty()) {
        if (largeHeader)
            BigTitleText(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 4.dp),
                label = stringResource,
                textColor = textColor[0]
            )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            val displayedPersons = if (isExpanded) {
                personsMap
            } else {
                personsMap.entries.take(initialVisibleCount).associate { it.toPair() }
            }

            buildAnnotatedString {
                if (!largeHeader)
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor[0],
                            baselineShift = BaselineShift.Subscript
                        )
                    ) {
                        append("$stringResource: ")
                    }

                displayedPersons.entries.forEachIndexed { index, (name, id) ->
                    withStyle(
                        style = SpanStyle(
                            textDecoration = TextDecoration.Underline,
                            color = textColor[1],
                            fontSize = 16.sp,
                            baselineShift = BaselineShift.Subscript,
                            letterSpacing = 0.5.sp
                        )
                    ) {
                        appendInlineContent("$index", name)
                    }
                    addStringAnnotation(
                        tag = "personId",
                        annotation = "$id",
                        start = length - name.length,
                        end = length
                    )
                    if (index < displayedPersons.size - 1) {
                        withStyle(
                            style = SpanStyle(
                                color = textColor[1],
                                fontSize = 16.sp,
                                baselineShift = BaselineShift.Subscript
                            )
                        ) {
                            append(", ")
                        }
                    }
                }

                if (!isExpanded && personsMap.size > initialVisibleCount) {
                    if (displayedPersons.isNotEmpty()) {
                        withStyle(
                            style = SpanStyle(
                                color = textColor[1],
                                fontSize = 16.sp,
                                baselineShift = BaselineShift.Subscript
                            )
                        ) {
                            append(", ...")
                        }
                    }

                    withStyle(
                        style = SpanStyle(
                            color = textColor[1],
                            fontSize = 16.sp,
                            baselineShift = BaselineShift.Subscript,
                            letterSpacing = 0.5.sp
                        )
                    ) {
                        append(" more")
                    }
                    addStringAnnotation(
                        tag = "more",
                        annotation = "expand",
                        start = length - 4,
                        end = length
                    )
                }
            }.let { annotatedString ->
                ClickableText(
                    text = annotatedString,
                    style = TextStyle(color = textColor[1]),
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            "more",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            isExpanded = true
                            return@ClickableText
                        }

                        annotatedString.getStringAnnotations(
                            "personId",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let { (item, _, _, _) ->
                            navigator.push(PersonDescriptionScreen(item.toInt()))
                        }
                    }
                )
            }
        }
    }
}