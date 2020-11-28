package com.hamidreza.newsapp.utils

sealed class ResultWrapper<T>(val data: T?, val msg: String?){
    class Loading<T> : ResultWrapper<T>(null, null)
    class Success<T>(data: T?) : ResultWrapper<T>(data,null)
    class Error<T>(msg:String) : ResultWrapper<T>(null,msg)
}
