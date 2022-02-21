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

        binding.loginBtnpic.setOnClickListener{
            val next = Intent(this,SignupFinActivity::class.java)
            startActivity(next)
        }
    }
}