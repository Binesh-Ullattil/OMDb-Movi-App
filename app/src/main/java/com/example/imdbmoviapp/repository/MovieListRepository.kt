package com.example.imdbmoviapp.repository

import com.example.imdbmoviapp.enums.ManageStateList
import androidx.lifecycle.MutableLiveData
import com.example.imdbmoviapp.R
import com.example.imdbmoviapp.apis.APIs
import com.example.imdbmoviapp.apis.ApiService
import com.example.imdbmoviapp.enums.ManageStatusEnum
import com.example.imdbmoviapp.model.Search
import com.example.imdbmoviapp.util.AppConstants
import com.example.imdbmoviapp.util.VolatileLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListRepository(var apiService: ApiService) {


    fun loadMovies(searchKey:String,paging:Int): MutableLiveData<ManageStateList<Search>>
    {
        val manageState: MutableLiveData<ManageStateList<Search>> = MutableLiveData()
        manageState.value= ManageStateList(ManageStatusEnum.LOADING)
        apiService.createService(APIs::class.java)
            .movieListWithPaging(AppConstants.API_KEY, AppConstants.SEARCH_TYPE, searchKey,paging)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response->
                manageState.value= ManageStateList(ManageStatusEnum.LOADING_DISMISS)
                if(response.Response == "True"){
                    if (!response.Search.isNullOrEmpty()) {
                        manageState.value = ManageStateList(status = ManageStatusEnum.SUCCESS,dataList = response.Search,responseStatus = response.Response,totalRecords = response.totalResults)
                    }else{
                        manageState.value= ManageStateList(status = ManageStatusEnum.NO_DATA_FOUND)
                    }
                }else{
                    manageState.value= ManageStateList(status = ManageStatusEnum.TOO_MANY_RESULTS)
                }
            }, {
                manageState.value= ManageStateList(ManageStatusEnum.LOADING_DISMISS)
                manageState.value= ManageStateList(status = ManageStatusEnum.FAILED,responseStatus = "False")
            })
        return manageState
    }
}