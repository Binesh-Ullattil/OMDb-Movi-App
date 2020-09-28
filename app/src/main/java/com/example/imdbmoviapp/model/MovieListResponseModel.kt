package com.example.imdbmoviapp.model

data class MovieListResponseModel(
    val Response: String,
    val Search: MutableList<Search>,
    val totalResults: String
)