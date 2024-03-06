package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO
import com.den.gorobets.getmovie.repo.MainRepository

class MovieDescriptionViewModel(private val mainRepo: MainRepository) : ViewModel() {

    fun getMovieDescription(movieId: Int, language: String): LiveData<DescriptionMovieDTO> =
        liveData {
            emit(mainRepo.getMovieDescription(movieId, language))
        }
}