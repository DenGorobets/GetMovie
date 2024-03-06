package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.den.gorobets.getmovie.repo.MainRepository
import com.example.lesson1.data.pojo_tmdb.search.SearchListMovieDTO

class SearchMovieViewModel(private val repository: MainRepository) : ViewModel() {

    fun searchMovies(searchRequest: String, language: String): LiveData<SearchListMovieDTO> =
        liveData {
            emit(repository.getSearchedMovies(searchRequest, language))
        }
}