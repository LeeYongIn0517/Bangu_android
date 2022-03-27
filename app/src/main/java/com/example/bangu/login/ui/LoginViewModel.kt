package com.example.bangu.login.ui

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.login.data.LgRepository
import com.example.bangu.login.data.model.AccessToken
import com.example.bangu.login.data.model.LoginRequest
import com.example.bangu.login.data.model.LoginResponse
import com.example.bangu.Event

class LoginViewModel: ViewModel() {
    private val repo = LgRepository
    private var _getTokenOk = MutableLiveData<Event<String>>()
    val getTokenOk: LiveData<Event<String>> = _getTokenOk
    fun getKakaoAuthCode(){
        repo.getKakaoAuthCode(object : LgRepository.GetDataCallback<AccessToken>{
            override fun onSuccess(data: AccessToken?) {
                if(data != null){
                    //회원가입 or 로그인에 사용할 토큰->sharedPreferences로 저장
                    App.token_prefs.accessToken = data.accessToken
                }
            }
            override fun onFailure(throwable: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun getLoginToken(email:String, password:String){
        val loginRequest = LoginRequest(
            email = email,
            password = password
        )
        repo.getLoginToken(loginRequest, object : LgRepository.GetDataCallback<LoginResponse>{
            override fun onSuccess(data: LoginResponse?) {
                if(data != null){
                    //저장소를 활용해 AccessToken으로 앱 기능이용 예정

                    //LiveData로 액티비티에 성공신호 제공
                    _getTokenOk.postValue(Event("getTokenOk"))
                }
            }
            override fun onFailure(throwable: Throwable) {
                //로그인 실패 - 빨간색 로그인박스로 바꾸기
                _getTokenOk.value = Event("getTokenFail")
            }
        })
    }
}