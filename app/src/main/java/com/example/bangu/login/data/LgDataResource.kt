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
        .baseUrl("https://bangu.shop:443") //도메인 주소
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val loginApi = retrofit.create(LoginAPI::class.java)
    /**/
    fun getKakaoToken(callback:LgRepository.GetDataCallback<AccessToken>){
        loginApi.getKakaoToken().enqueue(object :Callback<AccessToken>{
            override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
                if(response.isSuccessful){
                    callback.onSuccess(response.body())
                    Log.d("LgDataResource","getKakaoToken.onResponse")
                }
            }
            override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                callback.onFailure(t)
                Log.d("LgDataResource","getKakaoToken.onFailure")
            }
        })
    }
    /*로그인 요청*/
    fun getLoginToken(loginRequest:LoginRequest,callback:LgRepository.GetDataCallback<LoginResponse>){
        loginApi.getLoginToken(loginRequest).enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    Log.d("LgDataResource","getLoginToken.onResponse")
                    callback.onSuccess(response.body())
                }else{
                    val data = response.headers()
                    val data2 = response.body()
                    val data3 = response.message()
                    val data4 = response.code()
                    Log.d("LgDataResource","signupApi.getLoginToken.onResponse.callback-UnSuccessful")
                    Log.d("LgDataResource.header",data.toString())
                    Log.d("LgDataResource.body",data2.toString())
                    Log.d("LgDataResource.message",data3.toString())
                    Log.d("LgDataResource.code",data4.toString())
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("LgDataResource","getLoginToken.onFailure")
                callback.onFailure(t)
            }
        })
    }
}