package com.example.bangu.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.bangu.R

class IntroActivity : AppCompatActivity() {
    private val myHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val workThread = Thread(nextActivity())
        workThread.start()
    }
    inner class changeIntent:Runnable{
        override fun run() {
            val next = Intent(this@IntroActivity,LoginActivity::class.java)
            startActivity(next)
        }
    }
    inner class nextActivity:Runnable{
        override fun run() {
            myHandler.postDelayed(changeIntent(),2000)
        }
    }
}