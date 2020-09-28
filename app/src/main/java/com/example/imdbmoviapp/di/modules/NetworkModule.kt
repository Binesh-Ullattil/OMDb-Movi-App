package com.example.imdbmoviapp.di.modules

import android.content.Context
import com.example.imdbmoviapp.apis.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(context: Context): ApiService {
        return ApiService(context)
    }
}