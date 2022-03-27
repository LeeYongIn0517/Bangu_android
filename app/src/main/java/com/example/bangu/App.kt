package com.example.bangu

import android.app.Application
import com.example.bangu.login.data.TokenSharedPreferences

class App:Application() {
    companion object{
        lateinit var token_prefs : TokenSharedPreferences
    }

    override fun onCreate() {
        token_prefs = TokenSharedPreferences(applicationContext)
        super.onCreate()
    }
}