package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.ViewModel
import com.den.gorobets.getmovie.repo.MainRepository

class MovieDescriptionViewModel(private val mainRepo: MainRepository) : ViewModel() {

//    fun getMovieDescription(movieId: Int): LiveData<DescriptionMovieDTO> =
//        liveData {
//            emit(mainRepo.getMovieDescription(movieId))
//        }
}