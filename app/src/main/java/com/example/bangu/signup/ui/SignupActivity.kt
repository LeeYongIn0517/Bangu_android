package com.example.bangu.signup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.bangu.databinding.ActivitySignupBinding
import java.text.SimpleDateFormat
import java.util.*

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var nickname:String
    private lateinit var gender:String
    private var ott = mutableMapOf("tving" to false, "watcha" to false, "netflix" to false, "wavve" to false, "nothing" to false)
    private lateinit var createAt:String
    private lateinit var updateAt:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        val viewmodel = SignupViewModel()
        setContentView(view)

        //회원가입 요청
        binding.loginBtnpic.setOnClickListener{
            email = binding.signupEmail.text.toString()
            password = binding.signupPw.text.toString()
            nickname = binding.signupNickname.text.toString()
            ott.set("tving",if(binding.rdbtnTving.isChecked) true else false)
            ott.set("watcha",if(binding.rdbtnWatcha.isChecked) true else false)
            ott.set("netflix",if(binding.rdbtnNetflix.isChecked) true else false)
            ott.set("wavve",if(binding.rdbtnWavve.isChecked) true else false)
            ott.set("nothing",if(binding.rdbtnNothing.isChecked) true else false)
            gender = if(binding.rdbtnMale.isChecked) "M" else "F"
            var year = binding.datepicker.year
            var month = binding.datepicker.month + 1
            var day = binding.datepicker.dayOfMonth
            var birth = (year*10000 + month*100 + day).toLong()
            var now = System.currentTimeMillis()
            createAt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S",Locale.KOREAN).format(now)
            updateAt = createAt
            viewmodel.requestSignup(birth,createAt, email,gender,nickname,password,updateAt,ott)
            Log.d(" SignupActivity","just did viewmodel.requestSignup")
        }

        //회원가입 성공화면으로
        viewmodel.success.observe(this@SignupActivity, androidx.lifecycle.Observer {
            it.getSignIfEvented()?.let {
                Intent(this@SignupActivity,SignupFinActivity::class.java).apply {
                    startActivity(this)
                }
                Log.d(" SignupActivity","just did viewmodel.success.observe")
            }
        })
    }
}