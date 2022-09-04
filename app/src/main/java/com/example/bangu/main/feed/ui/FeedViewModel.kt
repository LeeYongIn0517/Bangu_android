package com.example.bangu.main.feed.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.login.data.LgDataResource
import com.example.bangu.main.data.MainDataResource
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.feed.data.FeedDataResource
import com.example.bangu.main.feed.data.FeedRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FeedViewModel: ViewModel() {
    private val ITEMS_SIZE = 20

    private val repo = FeedRepository
    //val adapter = FeedAdapter()

    private val MainService = MainDataResource.MainApi
    private var _reviewList = MutableLiveData<List<Content>>()
    val reviewList: LiveData<List<Content>> = _reviewList
    private var _BookMark = MutableLiveData<Event<String>>()
    val BookMark: LiveData<Event<String>> = _BookMark

    //팔로잉하는 리뷰어의 리뷰리스트 요청
    val accessToken = App.token_prefs.accessToken
    fun requestReviewList(page:Int, size:Int,type:String,disposables: CompositeDisposable){
        if (accessToken != null) {
            Log.d("accessToken",accessToken)
        }
        Log.d("FeedVM","requestReviewList")

        if (accessToken != null) {
            disposables.add(
                MainService.requestReviewList(accessToken,page,size,type)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({
                        Log.d("FeedViewModel","requestReviewList().success")
                        _reviewList.value = it.content
                    }){
                        Log.d("FeedViewModel","requestReviewList().fail")
                    }
            )
        }
    }
    fun adjustBookmark(reviewId:Int){
        if (accessToken != null) {
            repo.adjustBookmark(accessToken,reviewId)
        }
    }
}