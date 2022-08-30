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

class UpdatePwViewmodel:ViewModel() {
    private val profileService = ProfileDataResource.ProfileApi
    var accessToken = App.token_prefs.accessToken
    private var _updateOk = MutableLiveData<Event<Boolean>>()
    val  updateOk: LiveData<Event<Boolean>> = _updateOk

    fun checkPassword(input:String):Boolean{
        /**영어소문자, 숫자조합 3~20자리 검사*/
        if(Pattern.matches("^[a-z0-9]*.{3,20}$",input)){
            return true
        }
        return false
    }

    fun updatePassword(pw:String,disposables: CompositeDisposable){

        if(accessToken != null){
            disposables.add(
                profileService.updatePassword(accessToken!!,pw)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.i("UpdateViewModel","updatePassword().success")
                        _updateOk.postValue(Event(true))
                    }){
                        Log.i("UpdateViewModel","updatePassword().fail")
                    }
            )
        }
    }
}