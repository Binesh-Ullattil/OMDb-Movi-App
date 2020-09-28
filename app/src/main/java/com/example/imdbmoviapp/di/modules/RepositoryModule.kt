package com.example.imdbmoviapp.di.modules

import com.example.imdbmoviapp.apis.ApiService
import com.example.imdbmoviapp.repository.MovieDetailRepository
import com.example.imdbmoviapp.repository.MovieListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])

class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideMovieListRepository(
        apiService: ApiService
    ): MovieListRepository {
        return MovieListRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideMovieDetailRepository(
        apiService: ApiService
    ): MovieDetailRepository {
        return MovieDetailRepository(apiService)
    }

}