package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.gorobets.getmovie.database.SharedPreferencesManager
import com.den.gorobets.getmovie.repo.MainRepository
import com.den.gorobets.getmovie.utils.Resource
import com.den.gorobets.getmovie.utils.ui_state.MovieScreenUiState
import com.den.gorobets.getmovie.utils.view_state.MovieScreenViewState
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

    private val _MovieScreenViewState = MutableStateFlow(
        MovieScreenViewState(
            movieScreenUiState = MovieScreenUiState.Loading,
            isSnackbarVisible = false,
            isColorizedThemeEnabled = false
        )
    )
    val movieScreenViewState: StateFlow<MovieScreenViewState> =
        _MovieScreenViewState.asStateFlow()

    init {
        getMovieDescription()
        restoreSettings()
    }

    private fun getMovieDescription() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieDescription(movieId)
                .catch { error ->
                    _MovieScreenViewState.update { currentState ->
                        currentState.copy(
                            movieScreenUiState = MovieScreenUiState.Error(
                                error.message ?: "Unknown error"
                            )
                        )
                    }
                }
                .collect { result ->
                    _MovieScreenViewState.update { currentState ->
                        currentState.copy(
                            movieScreenUiState = when (result) {
                                is Resource.Success -> MovieScreenUiState.Success(result.data)
                                is Resource.Error -> MovieScreenUiState.Error(
                                    result.message ?: "Unknown error"
                                )

                                is Resource.Loading -> MovieScreenUiState.Loading
                            }
                        )
                    }
                }
        }
    }

    private fun restoreSettings() {
        val isSnackbarVisible = sharedPreferencesManager.getColorizedThemeSnackbarState()
        val isColorizedThemeEnabled = sharedPreferencesManager.getColorizedThemeState()
        _MovieScreenViewState.update {
            it.copy(
                isSnackbarVisible = isSnackbarVisible,
                isColorizedThemeEnabled = isColorizedThemeEnabled
            )
        }
    }

    fun setSnackbarVisibility() {
        sharedPreferencesManager.saveColorizedThemeSnackbarState()
        _MovieScreenViewState.update { it.copy(isSnackbarVisible = false) }
    }

    fun setColorizedThemeEnabled(enabled: Boolean) {
        sharedPreferencesManager.saveColorizedThemeState(enabled)
        _MovieScreenViewState.update { it.copy(isColorizedThemeEnabled = enabled) }
    }

    fun retryFetchDescription() {
        getMovieDescription()
    }
}