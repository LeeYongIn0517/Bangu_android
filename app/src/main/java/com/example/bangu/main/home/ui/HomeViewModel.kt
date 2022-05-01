package com.example.bangu.main.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.main.home.data.HomeRepository
import com.example.bangu.main.home.data.model.RequestReviewList

class HomeViewModel: ViewModel() {
    private val repo = HomeRepository
    private var _reviewList = MutableList<>() //데이터 클래스 미정
    private val ITEMS_SIZE = 3
    //리뷰리스트 요청
    val accessToken = App.token_prefs.accessToken
    fun requestReviewList(){
        Log.d("HomeVM","requestReviewList")
        if (accessToken != null) {
            repo.requestReviewList(accessToken,object:HomeRepository.GetDataCallback<RequestReviewList>{
                override fun onSuccess(data: RequestReviewList?) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(throwable: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

}