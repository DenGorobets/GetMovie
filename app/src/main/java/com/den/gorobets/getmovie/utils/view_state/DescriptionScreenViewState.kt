package com.den.gorobets.getmovie.utils.view_state

import com.den.gorobets.getmovie.utils.ui_state.DescriptionScreenUiState

data class DescriptionScreenViewState(
    val descriptionScreenUiState: DescriptionScreenUiState,
    val isSnackbarVisible: Boolean,
    val isColorizedThemeEnabled: Boolean
)
