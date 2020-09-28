package com.example.imdbmoviapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.imdbmoviapp.repository.MovieListRepository
import com.example.imdbmoviapp.viewModel.MovieListViewModel
import com.example.imdbmoviapp.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MovieListModule {

    @Provides
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    fun providesMovieListViewModel(repository: MovieListRepository): ViewModel {
        return MovieListViewModel(repository)
    }

}