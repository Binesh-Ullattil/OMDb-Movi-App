package com.example.imdbmoviapp.viewModel

import com.example.imdbmoviapp.enums.ManageStateList
import androidx.lifecycle.MutableLiveData
import com.example.imdbmoviapp.base.BaseViewModel
import com.example.imdbmoviapp.model.Search
import com.example.imdbmoviapp.repository.MovieListRepository

class MovieListViewModel(var repository: MovieListRepository): BaseViewModel() {

    var offSet: Int = 1
    var search: String = "Marvel"

     fun loadMovies(withIncrement: Boolean = true):MutableLiveData<ManageStateList<Search>>{
        if(withIncrement) offSet+=1
        return repository.loadMovies(search,offSet)
    }
}