package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.gorobets.getmovie.database.SharedPreferencesManager
import com.den.gorobets.getmovie.repo.MainRepository
import com.den.gorobets.getmovie.utils.Resource
import com.den.gorobets.getmovie.utils.ui_state.SeriesScreenUiState
import com.den.gorobets.getmovie.utils.view_state.SeriesScreenViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SeriesDescriptionViewModel(
    private val repository: MainRepository,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val seriesId: Int
) : ViewModel() {

    private val _SeriesScreenViewState = MutableStateFlow(
        SeriesScreenViewState(
            seriesScreenUiState = SeriesScreenUiState.Loading,
            isSnackbarVisible = false,
            isColorizedThemeEnabled = false
        )
    )
    val seriesScreenViewState: StateFlow<SeriesScreenViewState> =
        _SeriesScreenViewState.asStateFlow()

    init {
        getSeriesDescription()
        restoreSettings()
    }

    private fun getSeriesDescription() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSeriesDescription(seriesId)
                .catch { error ->
                    _SeriesScreenViewState.update { currentState ->
                        currentState.copy(
                            seriesScreenUiState = SeriesScreenUiState.Error(
                                error.message ?: "Unknown error"
                            )
                        )
                    }
                }
                .collect { result ->
                    _SeriesScreenViewState.update { currentState ->
                        currentState.copy(
                            seriesScreenUiState = when (result) {
                                is Resource.Success -> SeriesScreenUiState.Success(result.data)
                                is Resource.Error -> SeriesScreenUiState.Error(
                                    result.message ?: "Unknown error"
                                )

                                is Resource.Loading -> SeriesScreenUiState.Loading
                            }
                        )
                    }
                }
        }
    }

    private fun restoreSettings() {
        val isSnackbarVisible = sharedPreferencesManager.getColorizedThemeSnackbarState()
        val isColorizedThemeEnabled = sharedPreferencesManager.getColorizedThemeState()
        _SeriesScreenViewState.update {
            it.copy(
                isSnackbarVisible = isSnackbarVisible,
                isColorizedThemeEnabled = isColorizedThemeEnabled
            )
        }
    }

    fun setSnackbarVisibility() {
        sharedPreferencesManager.saveColorizedThemeSnackbarState()
        _SeriesScreenViewState.update { it.copy(isSnackbarVisible = false) }
    }

    fun setColorizedThemeEnabled(enabled: Boolean) {
        sharedPreferencesManager.saveColorizedThemeState(enabled)
        _SeriesScreenViewState.update { it.copy(isColorizedThemeEnabled = enabled) }
    }

    fun retryFetchDescription() {
        getSeriesDescription()
    }
}