package com.example.bangu.main.mybangu.ui.MyBangu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.mybangu.data.MyBanguRepository

class MyBanguViewModel:ViewModel() {
    private val repo = MyBanguRepository
    private var _reviewList = MutableLiveData<List<Content>>()
    val  reviewList: LiveData<List<Content>> = _reviewList

    /*내가 쓴 리뷰 리스트 요청*/
    val accessToken = App.token_prefs.accessToken
    fun requestMyReviews(page:Int, size:Int,type:String){
        if (accessToken != null) {
            Log.d("accessToken",accessToken)
        }
        Log.d("MyBanguVM","requestMyReviews")
        if (accessToken != null) {
            repo.requestMyReviews(accessToken,page,size,type,object :MyBanguRepository.GetDataCallback<RequestReviewList>{
                override fun onSuccess(data: RequestReviewList?) {
                    Log.d("MyBanguVM.requestMyReviews","onSuccess")
                    if (data != null) {
                        _reviewList.value = data.content
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("MyBanguVM.requestMyReviews","onFailure")
                }
            })
        }
    }
}