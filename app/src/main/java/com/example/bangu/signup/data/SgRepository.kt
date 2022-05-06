package com.example.bangu.signup.data

import android.util.Log
import com.example.bangu.signup.data.model.SignupRequest
import com.example.bangu.signup.data.model.SignupResponse

object SgRepository {
    private val sgDr = SgDataResource
    /*아이디 중복확인*/
    fun checkUserId(userIdText:String,callback: GetDataCallback<Boolean>){
        Log.d("SgRepository","checkUserId")
        sgDr.checkUserId(userIdText,callback)
    }
    /*닉네임 중복확인*/
    fun checkNickname(nickname:String,callback: GetDataCallback<Boolean>){
        Log.d("SgRepository","checkNickname")
        sgDr.checkNickname(nickname,callback)
    }
    /*회원가입*/
    fun requestSignup(signupRequest: SignupRequest, callback:GetDataCallback<SignupResponse>){
        Log.d("SgRepository","remoteDataService.requestSignup")
        sgDr.requestSignup(signupRequest,callback)
    }
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}