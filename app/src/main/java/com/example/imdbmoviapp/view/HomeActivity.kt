package com.example.imdbmoviapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.imdbmoviapp.R
import com.example.imdbmoviapp.base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onBaseCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)

        findNavController(R.id.fragment).navigate(R.id.movieListFragment)

    }
}
