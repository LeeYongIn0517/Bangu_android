package com.example.bangu.main.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.main.home.data.HomeRepository
import com.example.bangu.main.home.data.model.Content
import com.example.bangu.main.home.data.model.RequestReviewList

class HomeViewModel: ViewModel() {
    private val repo = HomeRepository
    private var _reviewList = MutableLiveData<List<Content>>()
    val  reviewList: LiveData<List<Content>> = _reviewList

    //리뷰리스트 요청
    val accessToken = App.token_prefs.accessToken
    fun requestReviewList(page:Int, size:Int,type:String,){
        if (accessToken != null) {
            Log.d("accessToken",accessToken)
        }
        Log.d("HomeVM","requestReviewList")
        if (accessToken != null) {
            repo.requestReviewList(accessToken,page,size,type,object:HomeRepository.GetDataCallback<RequestReviewList>{
                override fun onSuccess(data: RequestReviewList?) {
                    Log.d("HomeVM","onSuccess")
                    if(data != null){
                        _reviewList.value = data.content
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("HomeVM","onFailure")
                }
            })
        }
    }

}