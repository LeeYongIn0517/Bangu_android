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
import com.example.bangu.main.data.model.MovieResponseData
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
    private var _movieList = MutableLiveData<List<MovieResponseData>>()
    val movieList:LiveData<List<MovieResponseData>> = _movieList
    private var _searchReviews = MutableLiveData<List<Content>>()
    val searchReviews: LiveData<List<Content>> = _searchReviews
    //팔로잉하는 리뷰어의 리뷰리스트 요청
    val accessToken = App.token_prefs.accessToken

    fun requestReviewList(page:Int, size:Int,type:String,disposables: CompositeDisposable){
        if (accessToken != null) {
            disposables.add(
                MainService.requestReviewList(accessToken,page,size,type)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("FeedViewModel","requestReviewList().success")
                        _reviewList.value = it.content
                    }){
                        Log.d("FeedViewModel","requestReviewList().fail")
                    }
            )
        }
    }
    /**영화 이름으로 작품 검색*/
    fun searchMovie(name:String, page: Int, size: Int,disposable: CompositeDisposable){
        if(accessToken != null) {
            disposable.add(
                MainService.searchMovies(name, page, size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("FeedViewModel","searchMovies().success")
                        _movieList.value = it.content
                    }){
                        Log.d("FeedViewModel","searchMovies().fail")
                    }
            )
        }
    }
    /**영화 이름으로 리뷰 검색*/
    fun searchReviews(title:String,sortType:Boolean,disposables: CompositeDisposable){
        if(accessToken != null){
            disposables.add(
                MainService.searchReviews(accessToken,title,sortType)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("FeedViewModel","searchReviews().success")
                        _searchReviews.value = it
                    }){
                        Log.d("FeedViewModel","searchReviews().fail")
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