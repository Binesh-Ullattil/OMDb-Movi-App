package com.example.imdbmoviapp

import android.app.Application
import com.example.imdbmoviapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(),HasAndroidInjector {

    companion object{
        lateinit var app: App
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    private fun initDI(){
        DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        initDI()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
}