package com.example.bangu.main.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.main.home.data.HomeRepository
import com.example.bangu.main.home.data.model.Content
import com.example.bangu.main.home.data.model.RequestReviewList

class HomeViewModel: ViewModel() {
    private val repo = HomeRepository
    private var _reviewList = MutableLiveData<List<Content>>()
    val  reviewList: LiveData<List<Content>> = _reviewList
    private var _BookMark = MutableLiveData<Event<String>>()
    val BookMark: LiveData<Event<String>> = _BookMark

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
                    Log.d("HomeVM.requestReviewList","onSuccess")
                    if(data != null){
                        _reviewList.value = data.content
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("HomeVM.requestReviewList","onFailure")
                }
            })
        }
    }
    fun adjustBookmark(reviewId:Int){
        Log.d("HomeVM","adjustBookmark")
        if (accessToken != null) {
            repo.adjustBookmark(accessToken,reviewId, object:HomeRepository.GetDataCallback<Boolean>{
                override fun onSuccess(data: Boolean?) {
                    Log.d("HomeVM.adjustBookmark","onSuccess")
                    when(data){
                        true -> _BookMark.postValue(Event("bookmark_register"))
                        else -> _BookMark.postValue(Event("bookmark_cancel"))
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("HomeVM.adjustBookmark","onFailure")
                }
            })
        }
    }
    fun requestToUnFollow(toUser:Int){
        Log.d("HomeVM","requestToFollow")
        if (accessToken != null) {
            repo.requestToUnFollow(accessToken,toUser)
        }
    }
    fun requestToFollow(toUser:Int){
        Log.d("HomeVM","requestToFollow")
        if (accessToken != null) {
            repo.requestToFollow(accessToken,toUser)
        }
    }
}