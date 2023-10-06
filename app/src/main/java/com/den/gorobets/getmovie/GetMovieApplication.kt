package com.den.gorobets.getmovie

import android.app.Application
import com.den.gorobets.getmovie.api.AuthInterceptor
import com.den.gorobets.getmovie.api.Dispatcher
import com.den.gorobets.getmovie.api.OkHttpClient
import com.den.gorobets.getmovie.api.RetrofitClient.instanceRetrofit
import com.den.gorobets.getmovie.api.RetrofitClient.provideForecastApi
import com.den.gorobets.getmovie.repo.MainRepository
import com.den.gorobets.getmovie.repo.MainRepositoryImpl
import com.den.gorobets.getmovie.viewmodel.MovieDescriptionViewModel
import com.den.gorobets.getmovie.viewmodel.PersonDescriptionViewModel
import com.den.gorobets.getmovie.viewmodel.SearchMovieViewModel
import com.den.gorobets.getmovie.viewmodel.SeriesDescriptionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

open class GetMovieApplication : Application() {

    private val repoModule by lazy {

        module {
            single<MainRepository> { MainRepositoryImpl(get()) }
            viewModel { SearchMovieViewModel(get()) }
            viewModel { MovieDescriptionViewModel(get()) }
            viewModel { PersonDescriptionViewModel(get()) }
            viewModel { SeriesDescriptionViewModel(get()) }
        }
    }

    private val networkModule by lazy {

        module {
            factory { AuthInterceptor() }
            factory { Dispatcher().addDispatcher() }
            factory { OkHttpClient().provideOkHttpClient(get(), get()) }
            factory { provideForecastApi(get()) }
            single { instanceRetrofit(get()) }
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GetMovieApplication)
            modules(networkModule, repoModule)
        }
    }
}