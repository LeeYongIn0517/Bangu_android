package com.example.bangu.main.feed.data

import android.util.Log
import com.example.bangu.main.data.MainAPI
import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.home.data.HomeRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object FeedDataResource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bangu.shop:443")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    internal val MainApi = retrofit.create(MainAPI::class.java)
    internal val FeedApi = retrofit.create(FeedAPI::class.java)
    /*팔로잉하는 리뷰어의 리뷰 보기*/
    fun requestReviewList(accessToken:String,page:Int, size:Int,type:String){
        Log.d("HomeDataResource","requestReviewList")
    }
    fun adjustBookmark(accessToken:String,reviewId:Int) {
        Log.d("HomeDataResource", "adjustBookmark")
        FeedApi.adjustBookmark(accessToken, reviewId)
    }
}