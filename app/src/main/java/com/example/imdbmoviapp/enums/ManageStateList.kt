package com.example.imdbmoviapp.enums

class ManageStateList<T> (
    val status: ManageStatusEnum,
    val msg:String?=null,
    val dataList:MutableList<T>?=null,
    val responseStatus:String?=null,
    val offset:String?="",
    val totalRecords:String?=""
    ){

}