package com.example.imdbmoviapp.enums

class ManageStateData<T> (
    val status: ManageStatusEnum,
    val msg:String?=null,
    val data:T?=null,
    val responseStatus:String?=null
    ){
}