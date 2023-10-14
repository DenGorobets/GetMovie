package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.ViewModel
import com.den.gorobets.getmovie.repo.MainRepository

class SeriesDescriptionViewModel(private val mainRepo: MainRepository) : ViewModel() {

//    fun getSeriesDescription(seriesId: Int): LiveData<DescriptionSeriesDTO> =
//        liveData {
//            emit(mainRepo.getSeriesDescription(seriesId))
//        }
}