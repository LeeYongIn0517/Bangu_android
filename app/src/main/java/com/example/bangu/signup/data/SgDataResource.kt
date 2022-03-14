package com.example.bangu.signup.data

import android.util.Log
import android.widget.Toast
import com.example.bangu.signup.data.model.Signup
import com.example.bangu.signup.data.model.SignupResponse
import com.example.bangu.signup.ui.SignupActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

object SgDataResource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://3.34.255.216:8080")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val signupApi = retrofit.create(SignupAPI::class.java)
    /*아이디 중복확인*/
    fun checkUserEmail(emailText:String,callback: SgRepository.GetDataCallback<Boolean>){
        signupApi.checkUserEmail(emailText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            },{
                callback.onFailure(it)
            })
    }
    /*닉네임 중복확인*/
    fun checkNickname(nickname:String,callback: SgRepository.GetDataCallback<Boolean>){
        signupApi.checkNickname(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            },{
                callback.onFailure(it)
            })
    }
    /*회원가입*/
    fun requestSignup(signup: Signup, callback: SgRepository.GetDataCallback<SignupResponse>){
        signupApi.requestSignup(signup).enqueue(object:Callback<SignupResponse>{
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                if(response.isSuccessful){
                    callback.onSuccess(response.body())
                    Log.d("SgDataResource","just did signupApi.requestSignup and got onResponse")
                }
            }
            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}