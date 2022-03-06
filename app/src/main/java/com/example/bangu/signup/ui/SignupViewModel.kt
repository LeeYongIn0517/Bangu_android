package com.example.bangu.signup.ui

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.example.bangu.signup.data.SgRepository
import com.example.bangu.signup.data.model.SignupModel
import java.sql.Timestamp

class SignupViewModel(application: Application):AndroidViewModel(application) {
    private val repo = SgRepository

    fun requestSignup(birth:Long, createAt: String, email:String, gender:String, nickname:String,
                      password:String, updateAt: String, ott:MutableMap<String,Boolean>){
        repo.requestSignup(birth,createAt, email,gender,nickname,password,updateAt,ott, object : SgRepository.GetDataCallback<SignupModel>{
            override fun onSuccess(data: SignupModel?) {
                //회원가입 성공화면으로
                val next = Intent(,SignupFinActivity::class.java)
                startActivity(next)
            }

            override fun onFailure(throwable: Throwable) {
                //회원가입 실패
            }
       })
    }
}