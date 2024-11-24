package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.repo.MainRepository
import com.den.gorobets.getmovie.utils.Resource
import com.den.gorobets.getmovie.utils.ResponseEvents
import com.den.gorobets.getmovie.dto.discover.DiscoverListMovieDTO
import com.den.gorobets.getmovie.dto.discover.DiscoverListTVDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MainRepository) : ViewModel() {

    private var _nowPlayingMoviesList =
        MutableStateFlow<ResponseEvents<NowPlayingListMovieDTO>>(ResponseEvents.Loading())
    val nowPlayingMoviesList: StateFlow<ResponseEvents<NowPlayingListMovieDTO>> =
        _nowPlayingMoviesList.asStateFlow()

    private var _nowTrendingList =
        MutableStateFlow<ResponseEvents<TrendingListDTO>>(ResponseEvents.Loading())
    val nowTrendingList: StateFlow<ResponseEvents<TrendingListDTO>> =
        _nowTrendingList.asStateFlow()

    private var _discoverTVList =
        MutableStateFlow<ResponseEvents<DiscoverListTVDTO>>(ResponseEvents.Loading())
    val discoverTVList: StateFlow<ResponseEvents<DiscoverListTVDTO>> =
        _discoverTVList.asStateFlow()

    private var _discoverMovieList =
        MutableStateFlow<ResponseEvents<DiscoverListMovieDTO>>(ResponseEvents.Loading())
    val discoverMovieList: StateFlow<ResponseEvents<DiscoverListMovieDTO>> =
        _discoverMovieList.asStateFlow()

    init {
        nowPlayingMovies()
        nowTrending()
        discoverTV()
        discoverMovie()
    }

    fun retryRequests() {
        nowPlayingMovies()
        nowTrending()
        discoverTV()
        discoverMovie()
    }

    private fun nowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNowPlayingMovies().collect {
                when (it) {
                    is Resource.Error -> _nowPlayingMoviesList.value =
                        ResponseEvents.Failure(it.message ?: "Unknown error")

                    is Resource.Loading -> _nowPlayingMoviesList.value = ResponseEvents.Loading()
                    is Resource.Success -> _nowPlayingMoviesList.value =
                        ResponseEvents.Success(it.data)
                }
            }
        }
    }

    private fun nowTrending() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTrending().collect {
                when (it) {
                    is Resource.Error -> _nowTrendingList.value =
                        ResponseEvents.Failure(it.message ?: "Unknown error")

                    is Resource.Loading -> _nowTrendingList.value = ResponseEvents.Loading()
                    is Resource.Success -> _nowTrendingList.value =
                        ResponseEvents.Success(it.data)
                }
            }
        }
    }

    private fun discoverTV() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDiscoverTV().collect {
                when (it) {
                    is Resource.Error -> _discoverTVList.value =
                        ResponseEvents.Failure(it.message ?: "Unknown error")

                    is Resource.Loading -> _discoverTVList.value = ResponseEvents.Loading()
                    is Resource.Success -> _discoverTVList.value =
                        ResponseEvents.Success(it.data)
                }
            }
        }
    }

    private fun discoverMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDiscoverMovie().collect {
                when (it) {
                    is Resource.Error -> _discoverMovieList.value =
                        ResponseEvents.Failure(it.message ?: "Unknown error")

                    is Resource.Loading -> _discoverMovieList.value = ResponseEvents.Loading()
                    is Resource.Success -> _discoverMovieList.value =
                        ResponseEvents.Success(it.data)
                }
            }
        }
    }
}