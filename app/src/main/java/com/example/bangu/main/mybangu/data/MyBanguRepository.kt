package com.example.bangu.main.mybangu.data

import android.util.Log
import com.example.bangu.main.data.model.RequestReviewList

object MyBanguRepository {
    private val MyBanguDr = MyBanguDataResource
    fun requestMyReviews(accessToken: String,page:Int,size:Int,type:String,callback: GetDataCallback<RequestReviewList>){
        Log.d("MyBanguRepository","requestMyReviews")
        MyBanguDr.requestMyReviews(accessToken,page,size,type,callback)
    }
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}