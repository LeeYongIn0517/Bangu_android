package com.example.bangu.main.profile.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.main.data.MainDataResource
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.home.data.HomeRepository
import com.example.bangu.main.profile.data.ProfileDataResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BookmarkViewModel:ViewModel() {
    private val profileService = ProfileDataResource.ProfileApi
    private val mainService = MainDataResource.MainApi
    val accessToken = App.token_prefs.accessToken
    private var _reviewList = MutableLiveData<List<Content>>()
    val  reviewList: LiveData<List<Content>> = _reviewList

    /**유저가 북마크한 리뷰를 가져옵니다*/
    fun requestBookmark(page:Int, size:Int, disposable: CompositeDisposable){
        if (accessToken != null) {
            disposable.add(
                profileService.requestBookmark(accessToken, page, size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("BookmarkViewModel","requestBookmark().success")
                        _reviewList.value = it.content
                    }) {
                        Log.d("BookmarkViewModel","requestBookmark().fail")
                    }
            )
        }
    }
    fun adjustBookmark(reviewId:Int){
        Log.d("BookmarkViewModel","adjustBookmark")
    }
    /**언팔로우를 요청합니다*/
    fun requestToUnFollow(toUser:Int, disposable: CompositeDisposable){
        Log.d("BookmarkVM","requestToUnFollow")
        if (accessToken != null) {
            disposable.add(
                mainService.requestToUnFollow(accessToken,toUser)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("BookmarkViewModel","requestToUnFollow().success")
                    }){
                        Log.d("BookmarkViewModel","requestToUnFollow().fail")
                    }
            )
        }
    }
    /**팔로우를 요청합니다*/
    fun requestToFollow(toUser:Int, disposable: CompositeDisposable){
        Log.d("BookmarkViewModel","requestToFollow")
        if (accessToken != null) {
            disposable.add(
                mainService.requestToFollow(accessToken,toUser)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("BookmarkViewModel","requestToUnFollow().success")
                    }){
                        Log.d("BookmarkViewModel","requestToUnFollow().fail")
                    }
            )
        }
    }
}