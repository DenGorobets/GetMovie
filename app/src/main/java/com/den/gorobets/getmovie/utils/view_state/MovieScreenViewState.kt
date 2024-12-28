package com.den.gorobets.getmovie.utils.view_state

import com.den.gorobets.getmovie.utils.ui_state.MovieScreenUiState
import com.den.gorobets.getmovie.utils.ui_state.SeriesScreenUiState

data class MovieScreenViewState(
    val movieScreenUiState: MovieScreenUiState,
    val isSnackbarVisible: Boolean,
    val isColorizedThemeEnabled: Boolean
)

data class SeriesScreenViewState(
    val seriesScreenUiState: SeriesScreenUiState,
    val isSnackbarVisible: Boolean,
    val isColorizedThemeEnabled: Boolean
)