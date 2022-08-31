package com.example.bangu.main.profile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.main.profile.data.ProfileDataResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FollowingViewModel:ViewModel() {
    private val profileService = ProfileDataResource.ProfileApi
    val accessToken = App.token_prefs.accessToken
    val id = App.signup_prefs.sp_id //사용자 식별자

    fun requestFollowing(page:Int,size:Int,disposables: CompositeDisposable){
        if (accessToken != null) {
            disposables.add(
                profileService.requestFollowing(accessToken,id,page,size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("FollowingViewModel","requestFollowing().success")
                    }){
                        Log.d("FollowingViewModel","requestFollowing().fail")
                    }
            )
        }
    }
}