package com.example.imdbmoviapp.apis

import com.example.imdbmoviapp.model.MovieDetailResponseModel
import com.example.imdbmoviapp.model.MovieListResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIs {

    @GET("http://www.omdbapi.com/")
    fun movieList(
        @Query("apikey") apikey: String,
        @Query("type") type: String,
        @Query("s") s: String
    ): Observable<MovieListResponseModel>

    @GET("http://www.omdbapi.com/")
    fun movieListWithPaging(
        @Query("apikey") apikey: String,
        @Query("type") type: String,
        @Query("s") s: String,
        @Query("page") page:Int
    ): Observable<MovieListResponseModel>

    @GET("http://www.omdbapi.com/")
    fun movieDetails(
        @Query("apikey") apikey: String,
        @Query("i") i: String
    ): Observable<MovieDetailResponseModel>
}