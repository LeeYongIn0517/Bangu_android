package com.example.bangu.signup_fin.data

import android.util.Log
import com.example.bangu.signup_fin.data.model.SgFinMovieList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object SgFinDataResource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://3.34.255.216:8080")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val sgFinApi = retrofit.create(SgFinAPI::class.java)
    fun requestSgFinMovieList(page:Int,size:Int,callback: SgFinRepository.GetDataCallback<SgFinMovieList>){
        Log.d("SgFinDataResource","requestSgFinMovieList")
        sgFinApi.requestSgFinMovieList(page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            },{
                callback.onFailure(it)
            })
    }
}