package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.den.gorobets.getmovie.dto.description.series.DescriptionSeriesDTO
import com.den.gorobets.getmovie.repo.MainRepository

class SeriesDescriptionViewModel(private val mainRepo: MainRepository) : ViewModel() {

    fun getSeriesDescription(seriesId: Int, language: String): LiveData<DescriptionSeriesDTO> =
        liveData {
            emit(mainRepo.getSeriesDescription(seriesId, language))
        }
}