package com.example.imdbmoviapp.di.modules.builderModule

import androidx.lifecycle.ViewModelProvider
import com.example.imdbmoviapp.di.modules.MovieDetailModule
import com.example.imdbmoviapp.di.modules.MovieListModule
import com.example.imdbmoviapp.view.MovieDetailFragment
import com.example.imdbmoviapp.view.MovieListFragment
import com.example.imdbmoviapp.viewModel.MovieDetailViewModel
import com.example.imdbmoviapp.viewModel.MovieListViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [MovieDetailModule::class])
abstract class MovieDetailBuilderModule {

    @ContributesAndroidInjector(modules = [MovieDetailInjectorViewModel::class])
    abstract fun bind(): MovieDetailFragment

    @Module
    class MovieDetailInjectorViewModel {
        @Provides
        fun provideMovieListViewModelProvider(
            factory: ViewModelProvider.Factory,
            target: MovieDetailFragment
        ) = ViewModelProvider(target, factory).get(MovieDetailViewModel::class.java)
    }
}