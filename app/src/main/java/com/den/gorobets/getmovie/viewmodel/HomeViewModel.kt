package com.den.gorobets.getmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.repo.MainRepository
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListMovieDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListTVDTO

class HomeViewModel(private val repository: MainRepository) : ViewModel() {

    fun nowPlayingMovies(language: String): LiveData<NowPlayingListMovieDTO> =
        liveData {
            emit(repository.getNowPlayingMovies(language))
        }

    fun nowTrending(timeWindow: String, language: String): LiveData<TrendingListDTO> =
        liveData {
            emit(repository.getTrending(timeWindow, language))
        }

    fun discoverTV(language: String): LiveData<DiscoverListTVDTO> =
        liveData {
            emit(repository.getDiscoverTV(language))
        }

    fun discoverMovie(language: String): LiveData<DiscoverListMovieDTO> =
        liveData {
            emit(repository.getDiscoverMovie(language))
        }
}