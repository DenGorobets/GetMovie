package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.den.gorobets.getmovie.repo.MainRepository
import com.example.lesson1.data.pojo_tmdb.person.DescriptionPersonDTO

class PersonDescriptionViewModel(private val mainRepo: MainRepository) : ViewModel() {

    fun getPersonDescription(personId: Int, language: String): LiveData<DescriptionPersonDTO> =
        liveData {
            emit(mainRepo.getPersonDescription(personId, language))
        }
}