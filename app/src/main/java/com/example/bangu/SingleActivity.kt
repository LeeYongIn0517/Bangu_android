package com.example.bangu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.bangu.databinding.ActivitySingleBinding
import com.example.bangu.login.ui.LoginFragment
import com.example.bangu.main.ui.MainFragment

class SingleActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySingleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        installSplashScreen()
        if(!App.token_prefs.accessToken.equals("")){ //토큰이 존재하면 메인페이지를 추가하며 시작
            supportFragmentManager.beginTransaction().add(R.id.singleFrame,MainFragment())
        }else{
            supportFragmentManager.beginTransaction().add(R.id.singleFrame,LoginFragment()) //로그인 화면으로 가서 회원가입 유도
        }

    }
}