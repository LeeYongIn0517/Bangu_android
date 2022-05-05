package com.example.bangu.main.home.data

import android.util.Log
import com.example.bangu.main.data.model.RequestReviewList

object HomeRepository {
    private val HomeDr = HomeDataResource
    fun requestReviewList(accessToken: String,page:Int,size:Int,type:String,callback: GetDataCallback<RequestReviewList>){
        Log.d("HomeRepository","requestReviewList")
        HomeDr.requestReviewList(accessToken,page,size,type,callback)
    }
    fun adjustBookmark(accessToken:String,reviewId:Int,callback: GetDataCallback<Boolean>){
        Log.d("HomeRepository","adjustBookmark")
        HomeDr.adjustBookmark(accessToken,reviewId, callback)
    }
    fun requestToUnFollow(accessToken:String,toUser:Int){
        Log.d("HomeRepository","requestToFollowing")
        HomeDr.requestToUnFollow(accessToken,toUser)
    }
    fun requestToFollow(accessToken:String,toUser:Int){
        Log.d("HomeRepository","requestToFollow")
        HomeDr.requestToFollow(accessToken,toUser)
    }
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}