package com.den.gorobets.getmovie.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.gorobets.getmovie.database.SharedPreferencesManager
import com.den.gorobets.getmovie.repo.MainRepository
import com.den.gorobets.getmovie.utils.Resource
import com.den.gorobets.getmovie.utils.ui_state.DescriptionScreenUiState
import com.den.gorobets.getmovie.utils.view_state.DescriptionScreenViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDescriptionViewModel(
    private val repository: MainRepository,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val movieId: Int
) : ViewModel() {

    private val _DescriptionScreenViewState = MutableStateFlow(
        DescriptionScreenViewState(
            descriptionScreenUiState = DescriptionScreenUiState.Loading,
            isSnackbarVisible = false,
            isColorizedThemeEnabled = false
        )
    )
    val descriptionScreenViewState: StateFlow<DescriptionScreenViewState> =
        _DescriptionScreenViewState.asStateFlow()

    init {
        Log.e("MovieDescriptionViewModel", "init")
        getMovieDescription()
        restoreSettings()
    }

    private fun getMovieDescription() {
        Log.e("MovieDescriptionViewModel", "getMovieDescription")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieDescription(movieId)
                .catch { error ->
                    _DescriptionScreenViewState.update { currentState ->
                        currentState.copy(
                            descriptionScreenUiState = DescriptionScreenUiState.Error(
                                error.message ?: "Unknown error"
                            )
                        )
                    }
                }
                .collect { result ->
                    Log.e("MovieDescriptionViewModel", "getMovieDescription2: $result")
                    _DescriptionScreenViewState.update { currentState ->
                        currentState.copy(
                            descriptionScreenUiState = when (result) {
                                is Resource.Success -> DescriptionScreenUiState.Success(result.data)
                                is Resource.Error -> DescriptionScreenUiState.Error(
                                    result.message ?: "Unknown error"
                                )

                                is Resource.Loading -> DescriptionScreenUiState.Loading
                            }
                        )
                    }
                }
        }
    }

    private fun restoreSettings() {
        val isSnackbarVisible = sharedPreferencesManager.getColorizedThemeSnackbarState()
        val isColorizedThemeEnabled = sharedPreferencesManager.getColorizedThemeState()
        _DescriptionScreenViewState.update {
            it.copy(
                isSnackbarVisible = isSnackbarVisible,
                isColorizedThemeEnabled = isColorizedThemeEnabled
            )
        }
    }

    fun setSnackbarVisibility() {
        sharedPreferencesManager.saveColorizedThemeSnackbarState()
        _DescriptionScreenViewState.update { it.copy(isSnackbarVisible = false) }
    }

    fun setColorizedThemeEnabled(enabled: Boolean) {
        sharedPreferencesManager.saveColorizedThemeState(enabled)
        _DescriptionScreenViewState.update { it.copy(isColorizedThemeEnabled = enabled) }
    }

    fun retryFetchDescription() {
        getMovieDescription()
    }
}