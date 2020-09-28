package com.example.imdbmoviapp.repository

import com.example.imdbmoviapp.enums.ManageStateData
import androidx.lifecycle.MutableLiveData
import com.example.imdbmoviapp.apis.APIs
import com.example.imdbmoviapp.apis.ApiService
import com.example.imdbmoviapp.enums.ManageStatusEnum
import com.example.imdbmoviapp.model.MovieDetailResponseModel
import com.example.imdbmoviapp.util.AppConstants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailRepository(var apiService: ApiService) {

    fun getMovieDetails(movieId:String): MutableLiveData<ManageStateData<MovieDetailResponseModel>>
    {
        val manageState: MutableLiveData<ManageStateData<MovieDetailResponseModel>> = MutableLiveData()

        manageState.value= ManageStateData(ManageStatusEnum.LOADING)

        apiService.createService(APIs::class.java)
            .movieDetails(AppConstants.API_KEY,movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response->
                manageState.value= ManageStateData(ManageStatusEnum.LOADING_DISMISS)
                if(response!=null){
                    if(response.Response=="True"){
                        manageState.value = ManageStateData(status = ManageStatusEnum.SUCCESS,data = response,responseStatus = response.Response)
                    }else{
                        manageState.value = ManageStateData(status = ManageStatusEnum.SUCCESS,data = response,responseStatus = response.Response)
                    }
                }else{
                    manageState.value= ManageStateData(status = ManageStatusEnum.NO_DATA_FOUND,responseStatus = "False")
                }

            }, {
                manageState.value= ManageStateData(ManageStatusEnum.LOADING_DISMISS)
                manageState.value= ManageStateData(status = ManageStatusEnum.FAILED,responseStatus = "False")
            })

        return manageState
    }
}