package com.example.bangu.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bangu.R
import com.example.bangu.databinding.ActivityLoginBinding
import com.example.bangu.login.data.LgRepository
import com.example.bangu.signup.ui.SignupActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        var id = (binding.loginId.toString()).toLongOrNull() //숫자가 아니면 null을 반환
//        var pw = (binding.loginPw.toString()).toCharArray()
//        //id, pw 조건 검사 필요
//
//        //Call로 받은 응답-> LgDataResource의 응답인터페이스로 성공, 실패 상황 구현하기
//        val lgRepo = LgRepository
//        lgRepo.requestLogin()
        binding.loginSignUp.setOnClickListener{
            val next = Intent(this,SignupActivity::class.java)
            startActivity(next)
        }

    }
}