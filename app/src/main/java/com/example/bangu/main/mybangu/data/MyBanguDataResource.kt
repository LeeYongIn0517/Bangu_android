package com.example.bangu.main.mybangu.data

import android.util.Log
import com.example.bangu.main.data.MainAPI
import com.example.bangu.main.data.model.RequestReviewList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MyBanguDataResource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bangu.shop:443")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val MainApi = retrofit.create(MainAPI::class.java)

    fun requestMyReviews(accessToken:String, page:Int, size:Int, type:String,
        callback: MyBanguRepository.GetDataCallback<RequestReviewList>){
        Log.d("MyBanguDataResource","requestMyReviews")
        MainApi.requestReviewList(accessToken,page,size,type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            },{
                Log.d("MyBanguDataResource","Failure: " + it.localizedMessage)
                callback.onFailure(it)
            })
    }
}