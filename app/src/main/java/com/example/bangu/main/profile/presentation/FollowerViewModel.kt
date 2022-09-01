package com.example.bangu.main.profile.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.main.profile.data.ProfileDataResource
import com.example.bangu.main.profile.data.model.FollowContent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FollowerViewModel:ViewModel() {
    private val profileService = ProfileDataResource.ProfileApi
    private var _follower = MutableLiveData<List<FollowContent>>()
    val  follower: LiveData<List<FollowContent>> = _follower

    val accessToken = App.token_prefs.accessToken
    val id = App.signup_prefs.sp_id

    fun requestFollower(page:Int,size:Int,disposables: CompositeDisposable){
        if (accessToken != null) {
            disposables.add(
                profileService.requestFollower(accessToken, id, page, size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("FollowingViewModel", "requestFollower().success")
                        _follower.value = it.followData.content
                    }) {
                        Log.d("FollowingViewModel", "requestFollower().fail")
                    }
            )
        }
    }
}