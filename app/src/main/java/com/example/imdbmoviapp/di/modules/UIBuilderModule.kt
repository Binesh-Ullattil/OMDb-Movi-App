package com.example.imdbmoviapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imdbmoviapp.di.AppViewModelFactory
import com.example.imdbmoviapp.di.modules.builderModule.MovieDetailBuilderModule
import com.example.imdbmoviapp.di.modules.builderModule.MovieListBuilderModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [MovieListBuilderModule::class, MovieDetailBuilderModule::class])

class UIBuilderModule {

    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory =
        AppViewModelFactory(providers)

}