package com.den.gorobets.getmovie.ui.elements.home_view

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.den.gorobets.getmovie.R

@Composable
fun SeeAllButton(callback: () -> Unit) {
    Text(
        text = stringResource(R.string.see_all),
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.clickable { callback.invoke() }
    )
}