package com.example.bangu.signup_fin.data

import android.util.Log
import com.example.bangu.signup_fin.data.model.SgFinMovieList

object SgFinRepository {
    private val sgFinDr = SgFinDataResource
    fun requestSgFinMovieList(page:Int,size:Int,callback: GetDataCallback<SgFinMovieList>){
        Log.d("SgFinRepository","requestSgFinMovieList")
        sgFinDr.requestSgFinMovieList(page, size,callback)
    }
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}