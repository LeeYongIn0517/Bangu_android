package com.example.bangu.signup.data

import android.util.Log
import android.widget.Toast
import com.example.bangu.signup.data.model.Signup
import com.example.bangu.signup.data.model.SignupResponse
import com.example.bangu.signup.ui.SignupActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

object SgRepository {
    private val sgdr = SgDataResource
    /*아이디 중복확인*/
    fun checkUserEmail(emailText:String,callback: GetDataCallback<Boolean>){
        sgdr.checkUserEmail(emailText,callback)
    }
    /*닉네임 중복확인*/
    fun checkNickname(nickname:String,callback: GetDataCallback<Boolean>){
        sgdr.checkNickname(nickname,callback)
    }
    /*회원가입*/
    fun requestSignup(signup: Signup,callback:GetDataCallback<SignupResponse>){
        sgdr.requestSignup(signup,callback)
        Log.d("SgRepository","just did remoteDataService.requestSignup")
    }
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}