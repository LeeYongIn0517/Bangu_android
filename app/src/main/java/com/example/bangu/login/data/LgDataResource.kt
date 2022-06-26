package com.example.bangu.login.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.login.data.model.AccessToken
import com.example.bangu.login.data.model.LoginRequest
import com.example.bangu.login.data.model.LoginResponse
import com.example.bangu.login.ui.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object LgDataResource {
    var loginApi = Retrofit.Builder()
        .baseUrl("https://bangu.shop:443") //도메인 주소
        //받은 응답을 옵서버블 형태로 변환
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginAPI::class.java)
    private var _login_success = MutableLiveData<Event<String>>()
    val login_success:LiveData<Event<String>> = _login_success
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
}