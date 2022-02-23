package com.example.bangu.signup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bangu.R
import com.example.bangu.databinding.ActivityLoginBinding
import com.example.bangu.databinding.ActivitySignupBinding
import com.example.bangu.databinding.SignupFinItemBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //로그인
        binding.loginBtnpic.setOnClickListener{
            val next = Intent(this,SignupFinActivity::class.java)
            startActivity(next)
        }
        //id, pw 조건검사 로직
        //id는 영문,숫자,특수문자 조합 8-12자리

        //pw는 영문,숫자,특수문자 조합 8-20자리

        //pw 확인 로직도 필요함
    }
}