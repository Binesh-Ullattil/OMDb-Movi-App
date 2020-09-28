package com.example.imdbmoviapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.imdbmoviapp.R
import com.example.imdbmoviapp.util.NetworkStatusHelper
import com.example.imdbmoviapp.util.ToastHelper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(mainLooper)

        handler.postDelayed(Runnable {

            if(NetworkStatusHelper.isNetworkAvailable(this)){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                ToastHelper.showToast(this, getString(R.string.no_internet_connection))
            }

        }, 300)
    }
}
