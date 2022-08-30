package com.example.bangu.main.profile.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.main.profile.data.ProfileDataResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.regex.Pattern

class UpdateNicknameViewmodel:ViewModel() {
    private val profileService = ProfileDataResource.ProfileApi
    var accessToken = App.token_prefs.accessToken
    private var _updateOk = MutableLiveData<Event<Boolean>>()
    val  updateOk: LiveData<Event<Boolean>> = _updateOk

//    fun checkNickname(input:String):Boolean{
//        /**8자리 이하인지 검사*/
//        return input.length < 9
//    }

    fun updateNickname(nickname:String,disposables: CompositeDisposable){

        if(accessToken != null){
            disposables.add(
                profileService.updateNickname(accessToken!!,nickname)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.i("UpdateViewModel","updateNickname().success")
                        _updateOk.postValue(Event(true))
                    }){
                        Log.i("UpdateViewModel","updateNickname().fail")
                    }
            )
        }
    }
}