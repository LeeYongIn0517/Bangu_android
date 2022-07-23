package com.example.bangu.main.feed.data

import android.util.Log
import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.home.data.HomeDataResource
import com.example.bangu.main.home.data.HomeRepository

object FeedRepository {
    private val FeedDr = FeedDataResource
//    fun requestReviewList(accessToken: String,page:Int,size:Int,type:String){
//        Log.d("HomeRepository","requestReviewList")
//        FeedDr.requestReviewList(accessToken,page,size,type)
//    }
    fun adjustBookmark(accessToken:String,reviewId:Int){
        Log.d("HomeRepository","adjustBookmark")
        FeedDr.adjustBookmark(accessToken,reviewId)
    }
}