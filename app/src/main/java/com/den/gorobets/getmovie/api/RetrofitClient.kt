package com.den.gorobets.getmovie.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


const val TMBD_API_KEY =
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOGRiZDczMmJhZDRkYmRmZjYxNjcxYzc2MDQ4Mjk0MyIsInN1YiI6IjY0NjI4MDg3ZGJiYjQyMDExOWY1Yzc5NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xcTq0wjkjwJxtq3WFVmuHlTRJmr3jth-FLgxX2V8YM8"
const val TMBD_BASE_URL = "https://api.themoviedb.org/3/"
const val TMBD_IMAGE_BASE_URL =
    "https://image.tmdb.org/t/p/w500" //use "w500" instead "original" for less quality

object RetrofitClient {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    fun instanceRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(TMBD_BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(okHttpClient)
            .build()

    fun provideForecastApi(retrofit: Retrofit): RetrofitServices =
        retrofit.create(RetrofitServices::class.java)
}