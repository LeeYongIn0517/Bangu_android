package com.example.bangu.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.bangu.R
import com.example.bangu.databinding.ActivityLoginBinding
import com.example.bangu.login.data.LgRepository
import com.example.bangu.main.ui.MainActivity
import com.example.bangu.signup.ui.SignupActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@LoginActivity
            activity = this@LoginActivity
            var email = loginEmail.toString()
            var pw = loginPw.toString()
            //앱 자체 로그인인증 시작
            loginStartbtn.setOnClickListener{
                //로그인 실패할 경우 빨간 입력란으로 경고 <- 어댑터로 정리할 예정

                //로그인 성공할 경우
                val next = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(next)
                binding.invalidateAll()
            }
            //회원가입 페이지로
            loginSignUp.setOnClickListener{
                val next = Intent(this@LoginActivity,SignupActivity::class.java)
                startActivity(next)
            }
        }

//        //Call로 받은 응답-> LgDataResource의 응답인터페이스로 성공, 실패 상황 구현하기
//        val lgRepo = LgRepository
//        lgRepo.requestLogin()

    }
}