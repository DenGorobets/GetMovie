package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.den.gorobets.getmovie.repo.MainRepository
import com.example.lesson1.data.pojo_tmdb.search.SearchListMovieDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchMovieViewModel(private val repository: MainRepository) : ViewModel() {


    private var lastScrollIndex = 0

    private val _scrollUp = MutableStateFlow(false)
    val scrollUp: StateFlow<Boolean>
        get() = _scrollUp

    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == lastScrollIndex) return

        _scrollUp.value = newScrollIndex > lastScrollIndex
        lastScrollIndex = newScrollIndex
    }

    fun searchMovies(searchRequest: String, language: String): LiveData<SearchListMovieDTO> =
        liveData {
            emit(repository.getSearchedMovies(searchRequest, language))
        }
}