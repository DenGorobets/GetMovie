package com.den.gorobets.getmovie.extensions

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.dto.search.Result

fun Result.selectTitleResource(): String? =

    when (mediaType) {
        "movie" -> title
        "person" -> name
        "tv" -> name
        else -> title
    }

fun Result.selectImageResource(): String? =

    when (mediaType) {
        "movie" -> posterPath
        "person" -> profilePath
        "tv" -> posterPath
        else -> posterPath
    }

fun Result.selectYearResource(): String =

    when (mediaType) {
        "movie" -> releaseDate?.take(4) ?: ""
        "person" -> ""
        "tv" -> knownFor?.get(0)?.firstAirDate?.take(4) ?: ""
        else -> ""
    }

@Composable
fun Result.selectTypeResource(): String =

    when (mediaType) {
        "movie" -> stringResource(R.string.text_movie)
        "tv" -> stringResource(R.string.text_series)
        "person" ->
            when (knownForDepartment) {
                "Acting" -> stringResource(R.string.text_actor)
                "Sound" -> stringResource(R.string.text_sound_master)
                "Directing" -> stringResource(R.string.text_director)
                "Producer" -> stringResource(R.string.text_producer)
                else -> ""
            }

        else -> ""
    }

fun Pair<String, String>.displayAdditionalData(): String =

    when {
        first == "" -> second
        second == "" -> first
        else -> "${first}, $second"
    }

fun String.replaceOnDot(): String = replace("-", ".")

fun Context.openWebSite(website: String) {

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun Context.shareItem(presentText: String) {

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, presentText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Drawable.drawableToBitmap(): Bitmap {
    if (this is BitmapDrawable) {
        if (bitmap != null) {
            return bitmap.copy(Bitmap.Config.ARGB_8888, true)
        }
    }

    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}
