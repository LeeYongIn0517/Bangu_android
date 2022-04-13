package com.example.bangu.login.data

import android.util.Log
import com.example.bangu.login.data.model.AccessToken
import com.example.bangu.login.data.model.LoginRequest
import com.example.bangu.login.data.model.LoginResponse
import com.example.bangu.signup.data.SgDataResource
import com.example.bangu.signup.data.SignupAPI
import com.example.bangu.signup.data.model.SignupResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LgDataResource {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://3.34.255.216:8080") //도메인 주소
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val loginApi = retrofit.create(LoginAPI::class.java)
    fun getKakaoToken(callback:LgRepository.GetDataCallback<AccessToken>){
        loginApi.getKakaoToken().enqueue(object :Callback<AccessToken>{
            override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
                if(response.isSuccessful){
                    callback.onSuccess(response.body())
                    Log.d("LgDataResource","just did loginApi.getKakao and got onResponse")
                }
            }
            override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
    fun getLoginToken(email:String,password:String,callback:LgRepository.GetDataCallback<LoginResponse>){
        loginApi.getLoginToken(email,password).enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    callback.onSuccess(response.body())
                    Log.d("LgDataResource","just did loginApi.getLoginToken and got onResponse")
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}