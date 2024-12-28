package com.den.gorobets.getmovie.utils.ui_state

import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO
import com.den.gorobets.getmovie.dto.description.series.DescriptionSeriesDTO

sealed class MovieScreenUiState {
    data object Loading : MovieScreenUiState()
    data class Success(val description: DescriptionMovieDTO?) : MovieScreenUiState()
    data class Error(val errorMessage: String) : MovieScreenUiState()
}

sealed class SeriesScreenUiState {
    data object Loading : SeriesScreenUiState()
    data class Success(val description: DescriptionSeriesDTO?) : SeriesScreenUiState()
    data class Error(val errorMessage: String) : SeriesScreenUiState()
}