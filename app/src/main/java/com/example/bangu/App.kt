package com.example.bangu

import android.app.Application
import com.example.bangu.login.data.TokenSharedPreferences
import com.example.bangu.signup.data.model.SignupSharedPreferences
import org.conscrypt.Conscrypt
import java.security.Security

class App:Application() {
    companion object{
        lateinit var token_prefs : TokenSharedPreferences
        lateinit var signup_prefs: SignupSharedPreferences
    }

    override fun onCreate() {
        token_prefs = TokenSharedPreferences(applicationContext)
        signup_prefs = SignupSharedPreferences(applicationContext)
        Security.insertProviderAt(Conscrypt.newProvider(),1)
        super.onCreate()
    }
}