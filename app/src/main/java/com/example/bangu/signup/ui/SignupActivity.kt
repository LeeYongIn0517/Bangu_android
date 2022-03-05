package com.example.bangu.signup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bangu.databinding.ActivitySignupBinding
import java.sql.Timestamp

abstract class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var email:String
    private lateinit var pw:String
    private lateinit var nickname:String
    private var ott = mutableMapOf("tving" to false, "watcha" to false, "netflix" to false, "wavve" to false, "nothing" to false)
    private var gender = mutableMapOf("M" to false, "F" to false)
    abstract var birth:Long
    private lateinit var createAt:Timestamp
    private lateinit var updateAt:Timestamp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //회원가입 요청
        binding.loginBtnpic.setOnClickListener{
            email = binding.signupEmail.text.toString()
            pw = binding.signupPw.text.toString()
            nickname = binding.signupNickname.toString()
            ott.set("tving",if(binding.rdbtnTving.isChecked) true else false)
            ott.set("watcha",if(binding.rdbtnWatcha.isChecked) true else false)
            ott.set("netflix",if(binding.rdbtnNetflix.isChecked) true else false)
            ott.set("wavve",if(binding.rdbtnWavve.isChecked) true else false)
            ott.set("nothing",if(binding.rdbtnNothing.isChecked) true else false)
            if(binding.rdbtnMale.isChecked) gender.set("M", true)
            else gender.set("F", true)
            birth = binding.datepicker.maxDate
            Toast.makeText(this,"$birth",Toast.LENGTH_SHORT).show()
        }
    }
}