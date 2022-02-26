package com.example.bangu.signup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.R
import com.example.bangu.databinding.ActivitySignupBinding
import com.example.bangu.databinding.ActivitySignupFinBinding
import com.example.bangu.main.ui.MainActivity

class SignupFinActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupFinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupFinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signupfinRcyleview
            .adapter = SignupFinAdapter()

        binding.signupfinText.setOnClickListener{
            val next = Intent(this, MainActivity::class.java)
            startActivity(next)
        }
    }
}