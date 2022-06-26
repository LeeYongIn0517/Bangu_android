package com.example.bangu.login.data

import android.util.Log
import com.example.bangu.login.data.model.AccessToken
import com.example.bangu.login.data.model.LoginRequest
import com.example.bangu.login.data.model.LoginResponse

object LgRepository {
    private val lgDr = LgDataResource

    fun getKakaoAuthCode(callback:GetDataCallback<AccessToken>){
        Log.d("LgRepository","LgDataService.getKakaoAuthCode")
        lgDr.getKakaoToken(callback)
    }
    //로그인 요청에 대한 응답인터페이스
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}