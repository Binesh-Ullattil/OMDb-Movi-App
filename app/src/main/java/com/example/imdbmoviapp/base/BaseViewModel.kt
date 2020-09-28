package com.example.imdbmoviapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdbmoviapp.model.ToastState
import com.example.imdbmoviapp.util.ToastStateEnum

open class BaseViewModel: ViewModel() {

    var toastState = MutableLiveData<ToastState>()

    fun setInfoToast(messageResource: Int) {
        toastState.value = ToastState(ToastStateEnum.INFO, messageResource)
    }

    fun setSuccessToast(messageResource: Int) {
        toastState.value = ToastState(ToastStateEnum.SUCCESS, messageResource)
    }

    fun setFailureToast(messageResource: Int) {
        toastState.value = ToastState(ToastStateEnum.Failure, messageResource)
    }
}