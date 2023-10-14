package com.den.gorobets.getmovie.repo

import com.den.gorobets.getmovie.api.RetrofitServices
import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO
import com.den.gorobets.getmovie.dto.description.series.DescriptionSeriesDTO
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.utils.Resource
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListMovieDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListTVDTO
import com.example.lesson1.data.pojo_tmdb.person.DescriptionPersonDTO
import com.example.lesson1.data.pojo_tmdb.search.SearchListMovieDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepositoryImpl(private val movieRepo: RetrofitServices) : MainRepository {

    override suspend fun getSearchedMovies(request: String): Flow<Resource<SearchListMovieDTO>> =
        flow {
            emit(Resource.Loading())
            delay(5000)

            val response = movieRepo.getSearchListMovie(request)
            val responseBody = response.body()

            if (responseBody != null) {
                emit(Resource.Success(responseBody))
            } else {
                emit(Resource.Error(response.message() ?: "Unknown error"))
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(Resource.Error(e.message ?: "Connection error"))
        }

    override suspend fun getMovieDescription(movieId: Int): Flow<Resource<DescriptionMovieDTO>> =
        flow {
            emit(Resource.Loading())
            delay(5000)

            val response = movieRepo.getDescriptionMovie(movieId)
            val responseBody = response.body()

            if (responseBody != null) {
                emit(Resource.Success(responseBody))
            } else {
                emit(Resource.Error(response.message() ?: "Unknown error"))
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(Resource.Error(e.message ?: "Connection error"))
        }

    override suspend fun getSeriesDescription(seriesId: Int): Flow<Resource<DescriptionSeriesDTO>> =
        flow {
            emit(Resource.Loading())
            delay(5000)

            val response = movieRepo.getDescriptionSeries(seriesId)
            val responseBody = response.body()

            if (responseBody != null) {
                emit(Resource.Success(responseBody))
            } else {
                emit(Resource.Error(response.message() ?: "Unknown error"))
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(Resource.Error(e.message ?: "Connection error"))
        }


    override suspend fun getPersonDescription(personId: Int): Flow<Resource<DescriptionPersonDTO>> =
        flow {
            emit(Resource.Loading())
            delay(5000)

            val response = movieRepo.getDescriptionPerson(personId)
            val responseBody = response.body()

            if (responseBody != null) {
                emit(Resource.Success(responseBody))
            } else {
                emit(Resource.Error(response.message() ?: "Unknown error"))
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(Resource.Error(e.message ?: "Connection error"))
        }

    override suspend fun getNowPlayingMovies(): Flow<Resource<NowPlayingListMovieDTO>> =
        flow {
            emit(Resource.Loading())
            delay(5000)

            val response = movieRepo.getNowPlayingMovies()
            val responseBody = response.body()

            if (responseBody != null) {
                emit(Resource.Success(responseBody))
            } else {
                emit(Resource.Error(response.message() ?: "Unknown error"))
            }
        }.flowOn(Dispatchers.IO).catch { e ->
            emit(Resource.Error(e.message ?: "Connection error"))
        }

    override suspend fun getTrending(): Flow<Resource<TrendingListDTO>> = flow {
        emit(Resource.Loading())
        delay(5000)

        val response = movieRepo.getTrending()
        val responseBody = response.body()

        if (responseBody != null) {
            emit(Resource.Success(responseBody))
        } else {
            emit(Resource.Error(response.message() ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO).catch { e ->
        emit(Resource.Error(e.message ?: "Connection error"))
    }

    override suspend fun getDiscoverTV(): Flow<Resource<DiscoverListTVDTO>> = flow {
        emit(Resource.Loading())
        delay(5000)

        val response = movieRepo.getDiscoverTV()
        val responseBody = response.body()

        if (responseBody != null) {
            emit(Resource.Success(responseBody))
        } else {
            emit(Resource.Error(response.message() ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO).catch { e ->
        emit(Resource.Error(e.message ?: "Connection error"))
    }

    override suspend fun getDiscoverMovie(): Flow<Resource<DiscoverListMovieDTO>> = flow {
        emit(Resource.Loading())
        delay(5000)

        val response = movieRepo.getDiscoverMovie()
        val responseBody = response.body()

        if (responseBody != null) {
            emit(Resource.Success(responseBody))
        } else {
            emit(Resource.Error(response.message() ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO).catch { e ->
        emit(Resource.Error(e.message ?: "Connection error"))
    }
}

