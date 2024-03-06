package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.ViewModel
import com.den.gorobets.getmovie.repo.MainRepository

class PersonDescriptionViewModel(private val mainRepo: MainRepository) : ViewModel() {

//    fun getPersonDescription(personId: Int): LiveData<DescriptionPersonDTO> =
//        liveData {
//            emit(mainRepo.getPersonDescription(personId))
//        }
}