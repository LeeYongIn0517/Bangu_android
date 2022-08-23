package com.example.bangu.main.mybangu.ui.MyBangu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.mybangu.data.MyBanguDataResource
import com.example.bangu.main.mybangu.data.MyBanguRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyBanguViewModel:ViewModel() {
    private val mybanguService = MyBanguDataResource.MyBanguApi
    private val repo = MyBanguRepository
    private var _reviewList = MutableLiveData<List<Content>>()
    val  reviewList: LiveData<List<Content>> = _reviewList
    private var _deleteOk = MutableLiveData<Event<Boolean>>()
    val  deleteOk: LiveData<Event<Boolean>> = _deleteOk

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
    fun deleteMyReviews(target_id:Int,disposables: CompositeDisposable){
        if(accessToken != null){
            disposables.add(
                mybanguService.deleteMyReview(accessToken,target_id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.i("MyBanguViewModel","deleteMyReviews().success")
                        _deleteOk.postValue(Event(true))
                    }){
                        Log.i("MyBanguViewModel","deleteMyReviews().fail")
                        Log.d("SgDataResource.message",it.toString())
                    }
            )
        }
    }
}