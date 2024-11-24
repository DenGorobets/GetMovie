package com.den.gorobets.getmovie.utils.ui_state

import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO

sealed class DescriptionScreenUiState {
    data object Loading : DescriptionScreenUiState()
    data class Success(val description: DescriptionMovieDTO?) : DescriptionScreenUiState()
    data class Error(val errorMessage: String) : DescriptionScreenUiState()
}
