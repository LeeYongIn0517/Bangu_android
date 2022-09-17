package com.example.bangu.main.home.data

import android.util.Log
import com.example.bangu.main.data.MainAPI
import com.example.bangu.main.data.model.RequestReviewList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object HomeDataResource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bangu.shop:443")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val MainApi = retrofit.create(MainAPI::class.java)
    private val HomeApi = retrofit.create(HomeAPI::class.java)
    fun requestReviewList(accessToken:String,page:Int, size:Int,type:String,callback: HomeRepository.GetDataCallback<RequestReviewList>){
        Log.d("HomeDataResource","requestReviewList")
        MainApi.requestReviewList(accessToken,page,size,type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            },{
                Log.d("HomeDataResource","Failure: " + it.localizedMessage)
                callback.onFailure(it)
            })
    }
    fun adjustBookmark(accessToken:String,reviewId:Int,callback: HomeRepository.GetDataCallback<Boolean>){
        Log.d("HomeDataResource","adjustBookmark")
        HomeApi.adjustBookmark(accessToken,reviewId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            },{
                Log.d("HomeDataResource","Failure: " + it.localizedMessage)
                callback.onFailure(it)
            })
    }
    fun requestToFollow(
        accessToken:String,
        reviewId:Int,
        callback: HomeRepository.GetDataCallback<Any>
    ){
        Log.d("HomeDataResource", "requestToFollow")
        HomeApi.requestToFollow(accessToken,reviewId)
    }
    fun requestToUnFollow(
        accessToken:String,
        reviewId:Int,
        callback: HomeRepository.GetDataCallback<Any>
    ){
        Log.d("HomeDataResource", "requestToUnFollow")
        HomeApi.requestToUnFollow(accessToken,reviewId)
    }
}