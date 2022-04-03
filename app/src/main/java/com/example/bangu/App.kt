package com.example.bangu

import android.app.Application
import com.example.bangu.login.data.TokenSharedPreferences
import com.example.bangu.signup.data.model.SignupSharedPreferences

class App:Application() {
    companion object{
        lateinit var token_prefs : TokenSharedPreferences
        lateinit var signup_prefs: SignupSharedPreferences
    }

    override fun onCreate() {
        token_prefs = TokenSharedPreferences(applicationContext)
        signup_prefs = SignupSharedPreferences(applicationContext)
        super.onCreate()
    }
}