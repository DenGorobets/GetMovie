package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.ViewModel
import com.den.gorobets.getmovie.database.SharedPreferencesManager
import com.den.gorobets.getmovie.dto.description.ScrollerItemData
import com.den.gorobets.getmovie.repo.MainRepository

class SeriesSeasonsListViewModel(
    private val repository: MainRepository,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val seasonList: List<ScrollerItemData>
) : ViewModel() {

}