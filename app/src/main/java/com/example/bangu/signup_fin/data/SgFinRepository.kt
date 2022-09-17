package com.example.bangu.signup_fin.data

import android.util.Log
import com.example.bangu.signup_fin.data.model.SgFinMovieList

object SgFinRepository {
    private val sgFinDr = SgFinDataResource
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}