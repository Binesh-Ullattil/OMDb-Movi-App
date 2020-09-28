package com.example.imdbmoviapp.di.modules.builderModule

import androidx.lifecycle.ViewModelProvider
import com.example.imdbmoviapp.di.modules.MovieListModule
import com.example.imdbmoviapp.view.MovieListFragment
import com.example.imdbmoviapp.viewModel.MovieListViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [MovieListModule::class])
abstract class MovieListBuilderModule {

    @ContributesAndroidInjector(modules = [MovieListInjectorViewModel::class])
    abstract fun bind(): MovieListFragment

    @Module
    class MovieListInjectorViewModel {
        @Provides
        fun provideMovieListViewModelProvider(
            factory: ViewModelProvider.Factory,
            target: MovieListFragment
        ) = ViewModelProvider(target, factory).get(MovieListViewModel::class.java)
    }
}