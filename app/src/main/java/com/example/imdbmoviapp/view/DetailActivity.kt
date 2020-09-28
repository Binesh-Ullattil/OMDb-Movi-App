package com.example.imdbmoviapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.imdbmoviapp.R
import com.example.imdbmoviapp.util.AppConstants

class DetailActivity : AppCompatActivity() {

    var movieId:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        try {
            movieId = intent.extras?.getString(AppConstants.MOVIE_ID)!!
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val bundle = Bundle()
        bundle.putString(AppConstants.MOVIE_ID, movieId)
        findNavController(R.id.fragmentDetail).navigate(R.id.movieDetailFragment,bundle)

    }
}
