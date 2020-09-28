package com.example.imdbmoviapp.di

import android.content.Context
import com.example.imdbmoviapp.App
import com.example.imdbmoviapp.di.modules.NetworkModule
import com.example.imdbmoviapp.di.modules.RepositoryModule
import com.example.imdbmoviapp.di.modules.UIBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    AndroidInjectionModule::class,
    UIBuilderModule::class
])

@Singleton
interface AppComponent {
    fun inject(app:App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}