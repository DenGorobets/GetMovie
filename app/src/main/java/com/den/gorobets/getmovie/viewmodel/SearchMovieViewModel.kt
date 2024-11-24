package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.gorobets.getmovie.repo.MainRepository
import com.den.gorobets.getmovie.utils.Resource
import com.den.gorobets.getmovie.utils.ResponseEvents
import com.den.gorobets.getmovie.dto.search.SearchListMovieDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchMovieViewModel(private val repository: MainRepository) : ViewModel() {


    private var lastScrollIndex = 0

    private val _scrollUp = MutableStateFlow(false)
    val scrollUp: StateFlow<Boolean>
        get() = _scrollUp

    private var _searchMovies =
        MutableStateFlow<ResponseEvents<SearchListMovieDTO>>(ResponseEvents.Loading())
    val searchMovies: StateFlow<ResponseEvents<SearchListMovieDTO>> =
        _searchMovies.asStateFlow()

    fun geSearchedMovies(searchRequest: String) {
        viewModelScope.launch {
            repository.getSearchedMovies(searchRequest).collect {
                when (it) {
                    is Resource.Error -> _searchMovies.value =
                        ResponseEvents.Failure(it.message ?: "Unknown error")

                    is Resource.Loading -> _searchMovies.value = ResponseEvents.Loading()
                    is Resource.Success -> _searchMovies.value =
                        ResponseEvents.Success(it.data)
                }
            }
        }
    }

    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return

        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }
}