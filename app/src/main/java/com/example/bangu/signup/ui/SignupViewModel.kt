package com.example.bangu.signup.ui

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.example.bangu.signup.data.SgRepository
import com.example.bangu.signup.data.model.SignupModel
import java.sql.Timestamp

class SignupViewModel(application: Application):AndroidViewModel(application) {
    private val repo = SgRepository

    fun requestSignup(birth:String, create_at: Timestamp, email:String, gender:Char, netflix:Boolean, nickname:String,
                      password:String, tving:Boolean, update_at: Timestamp, watcha:Boolean, wavve:Boolean){
        repo.requestSignup(birth,create_at, email,gender,netflix,nickname,password,tving,update_at,watcha, wavve, object : SgRepository.GetDataCallback<SignupModel>{
            override fun onSuccess(data: SignupModel?) {
                //회원가입 성공화면으로
//                val next = Intent(this@SignupViewModel,SignupFinActivity::class.java)
//                startActivity(next)
            }

            override fun onFailure(throwable: Throwable) {
                //회원가입 실패
            }
       })
    }
}