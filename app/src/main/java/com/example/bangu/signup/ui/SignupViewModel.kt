package com.example.bangu.signup.ui

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.signup.data.SgRepository
import com.example.bangu.signup.data.model.SignupModel

class SignupViewModel:ViewModel(){
    private val repo = SgRepository
    private var _success = MutableLiveData<Event<Boolean>>()
    val success: LiveData<Event<Boolean>> = _success

    fun requestSignup(birth:Long, createAt: String, email:String, gender:String, nickname:String,
                      password:String, updateAt: String, ott:MutableMap<String,Boolean>){
        repo.requestSignup(birth,createAt, email,gender,nickname,password,updateAt,ott, object : SgRepository.GetDataCallback<SignupModel>{
            override fun onSuccess(data: SignupModel?){
                //액티비티에 성공신호 주기
                _success.value = Event(true)
                Log.d(" SignupViewModel","override fun onSuccess")
            }

            override fun onFailure(throwable: Throwable){
            //경고창 띄우기
                Log.d(" SignupViewModel","override fun onFailure")
            }
       })
    }
}