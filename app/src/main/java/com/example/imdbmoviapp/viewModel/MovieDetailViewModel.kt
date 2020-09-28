package com.example.imdbmoviapp.viewModel

import com.example.imdbmoviapp.enums.ManageStateData
import androidx.lifecycle.MutableLiveData
import com.example.imdbmoviapp.base.BaseViewModel
import com.example.imdbmoviapp.model.MovieDetailResponseModel
import com.example.imdbmoviapp.repository.MovieDetailRepository

class MovieDetailViewModel(var repository: MovieDetailRepository):BaseViewModel() {

     fun loadMovieDetails(id:String):MutableLiveData<ManageStateData<MovieDetailResponseModel>>{
        return repository.getMovieDetails(id)
    }
}
