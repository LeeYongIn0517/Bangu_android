package com.example.bangu.main.mybangu.data

import android.util.Log
import com.example.bangu.main.data.MainAPI
import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.mybangu.data.model.MovieSearchResponse
import com.example.bangu.main.mybangu.data.model.RegisterReview
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
    private val MainApi = retrofit.create(MainAPI::class.java) //리뷰 요청은 뷰페이저 4p 중 3p가 동일하게 사용하는 api이므로 MainAPI를 사용함
    private val MyBanguApi = retrofit.create(MyBanguAPI::class.java) //나머지 요청은 mybangu 전용api 사용함
    /**************************main-mybangu 내가 쓴 리뷰 페이지***************************/
    /*main-mybangu 페이지의 내가 쓴 리뷰 불러오기*/
    fun requestMyReviews(accessToken:String, page:Int, size:Int, type:String,
        callback: MyBanguRepository.GetDataCallback<RequestReviewList>){
        Log.d("MyBanguDataResource","requestMyReviews")
        MainApi.requestReviewList(accessToken,page,size,type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            },{
                Log.d("MyBanguDataResource.requestMyReviews","Failure: " + it.localizedMessage)
                callback.onFailure(it)
            })
    }
    /***************************main-mybangu 리뷰 작성 페이지****************************/
    fun registerMyReview(accessToken: String, registerReview: RegisterReview){
        Log.d("MyBanguDataResource","registerMyReviews")
        MyBanguApi.registerMyReview(accessToken, registerReview)
    }
    /***************************main-mybangu 영화작품 검색 페이지****************************/
    /*리뷰에 쓸 영화 작품 불러오기*/
    fun requestMovie(name:String,page:Int,size:Int,callback: MyBanguRepository.GetDataCallback<MovieSearchResponse>){
        Log.d("MyBanguDataResource","requestMovie")
        MyBanguApi.requestMovie(name,page,size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            },{
                Log.d("MyBanguDataResource.requestMovie","Failure: " + it.localizedMessage)
                callback.onFailure(it)
            })
    }
}