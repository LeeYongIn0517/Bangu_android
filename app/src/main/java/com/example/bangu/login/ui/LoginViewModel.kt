package com.example.bangu.login.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.login.data.LgRepository
import com.example.bangu.login.data.model.AccessToken
import com.example.bangu.login.data.model.LoginRequest
import com.example.bangu.login.data.model.LoginResponse
import com.example.bangu.Event

class LoginViewModel: ViewModel() {
    private val repo = LgRepository
    private var _success = MutableLiveData<Event<Boolean>>()
    val success: LiveData<Event<Boolean>> = _success

    fun getKakaoAuthCode(){
        repo.getKakaoAuthCode(object : LgRepository.GetDataCallback<AccessToken>{
            override fun onSuccess(data: AccessToken?) {
                if(data != null){
                    data.accessToken.toString()
                    //근데 이 토큰을 회원가입 or 로그인에 사용하면 됨
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
                //AccessToken으로 앱 기능이용 예정

                //LiveData로 액티비티에 성공신호 제공
                _success.value = Event(true)
                Log.d("LoginViewModel","override fun onSuccess")
            }
            override fun onFailure(throwable: Throwable) {
                //로그인 실패-빨간색 로그인박스로 바꾸기
            }
        })
    }
}