package com.example.imdbmoviapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.imdbmoviapp.repository.MovieDetailRepository
import com.example.imdbmoviapp.viewModel.MovieDetailViewModel
import com.example.imdbmoviapp.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MovieDetailModule {

    @Provides
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    fun providesMovieDetailViewModel(repository: MovieDetailRepository): ViewModel {
        return MovieDetailViewModel(repository)
    }

}