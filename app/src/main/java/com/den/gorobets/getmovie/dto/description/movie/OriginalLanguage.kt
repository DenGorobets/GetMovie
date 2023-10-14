package com.den.gorobets.getmovie.dto.description.movie

enum class OriginalLanguage(val value: String) {
    En("en"),
    Fr("fr"),
    Ja("ja"),
    Uk("uk");

    companion object {
        fun fromValue(value: String): OriginalLanguage = when (value) {
            "en" -> En
            "fr" -> Fr
            "ja" -> Ja
            "uk" -> Uk
            else -> throw IllegalArgumentException()
        }
    }
}