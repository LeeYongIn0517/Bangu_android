package com.example.bangu.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.bangu.R
import com.example.bangu.databinding.ActivityLoginBinding
import com.example.bangu.login.data.LgRepository
import com.example.bangu.login.data.model.AccessToken
import com.example.bangu.main.ui.MainActivity
import com.example.bangu.signup.ui.SignupActivity
import com.example.bangu.signup.ui.SignupFinActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var email:String
    private lateinit var password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        setContentView(binding.root)
        val viewmodel = LoginViewModel()
        val lgRepo = LgRepository

        binding.apply {
            lifecycleOwner = this@LoginActivity
            activity = this@LoginActivity

            //앱 자체 로그인인증 시작
            loginStartbtn.setOnClickListener{
                email = loginEmail.toString()
                password = loginPw.toString()
                viewmodel.getLoginToken(email,password)
            }
            //kako 로그인 버튼 눌렀을 때
            kakaoBtn.setOnClickListener{
                viewmodel.getKakaoAuthCode()
            }
            //회원가입 페이지로
            loginSignUp.setOnClickListener{
                val next = Intent(this@LoginActivity,SignupActivity::class.java)
                startActivity(next)
            }
        }
        viewmodel.getTokenOk.observe(this@LoginActivity, Observer {
            it.getIfEvented()?.let {
                //로그인 성공->메인 홈화면으로 (일반, kakao, naver 공통)
                if(it == "getTokenOk"){
                    Intent(this@LoginActivity, MainActivity::class.java).apply {
                        startActivity(this)
                    }
                }
                //로그인 실패-> 로그인 EditText UI변경
                else if(it == "getTokenFail"){
                    binding.apply {
                        loginEmail.setBackgroundResource(R.drawable.login_input_error)
                        loginPw.setBackgroundResource(R.drawable.login_input_error)
                        tvError.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}