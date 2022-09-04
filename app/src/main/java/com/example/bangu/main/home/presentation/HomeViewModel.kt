package com.example.bangu.main.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.main.data.MainDataResource
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.home.data.HomeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel: ViewModel() {
    private val repo = HomeRepository
    private val MainService = MainDataResource.MainApi
    private var _reviewList = MutableLiveData<List<Content>>()
    val  reviewList: LiveData<List<Content>> = _reviewList
    private var _BookMark = MutableLiveData<Event<String>>()
    val BookMark: LiveData<Event<String>> = _BookMark
    private var _search = MutableLiveData<List<Content>>()
    val  search: LiveData<List<Content>> = _search
    private var _movieList = MutableLiveData<List<MovieResponseData>>()
    val movieList:LiveData<List<MovieResponseData>> = _movieList
    //리뷰리스트 요청
    val accessToken = App.token_prefs.accessToken

    fun requestReviewList(page:Int, size:Int,type:String){
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
    /**영화 이름으로 리뷰 검색*/
    fun searchReviews(title:String,sortType:Boolean,disposable: CompositeDisposable){
        if (accessToken != null) {
            disposable.add(
            MainService.searchReviews(accessToken,title,sortType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("HomeViewModel","searchReviews().success")
                    _search.value = it
                }){
                    Log.d("HomeViewModel","searchReviews().fail")
                }
            )
        }
    }
    /**영화 이름으로 검색*/
    fun searchMovie(name:String, page: Int, size: Int,disposable: CompositeDisposable){
        if(accessToken != null) {
            disposable.add(
                MainService.searchMovies(name, page, size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("HomeViewModel","searchMovies().success")
                        _movieList.value = it.content
                    }){
                        Log.d("HomeViewModel","searchMovies().fail")
                    }
            )
        }
    }
}