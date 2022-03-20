package com.example.bangu.signup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bangu.R
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
        binding.apply {
            lifecycleOwner = this@SignupActivity
            activity = this@SignupActivity
        }
        //이메일 중복체크 버튼
        binding.emailCheckbtn.setOnClickListener {
                viewmodel.checkUserEmail(binding.signupEmail.text.toString())
        }
        //닉네임 중복체크 버튼
        binding.nicknameCheckbtn.setOnClickListener {
                viewmodel.checkNickname(binding.signupNickname.text.toString())
        }
        //이메일 중복확인 결과
        viewmodel.emailOk.observe(this@SignupActivity, androidx.lifecycle.Observer {
            it.getIfEvented()?.let {
                if(it == "emailOk"){
                    //성공
                    binding.apply {
                        emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn2)
                        emailCheckbtn.isEnabled = false
                        emailOk.visibility = View.VISIBLE
                        emailFail.visibility = View.INVISIBLE
                    }
                }
                else if(it == "emailFail"){
                    //실패
                    binding.apply {
                        emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                        emailCheckbtn.isEnabled = true
                        emailOk.visibility = View.INVISIBLE
                        emailFail.visibility = View.VISIBLE
                    }
                }
            }
        })
        //닉네임 중복확인 결과
        viewmodel.nicknameOk.observe(this@SignupActivity, androidx.lifecycle.Observer {
            it.getIfEvented()?.let {
                if(it == "nicknameOk"){
                    //성공
                    binding.apply {
                        nicknameCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn2)
                        nicknameCheckbtn.isEnabled = false
                        nicknameOk.visibility = View.VISIBLE
                        nicknameFail.visibility = View.INVISIBLE
                    }
                }
                else if(it == "nicknameFail"){
                    //실패
                    binding.apply {
                        nicknameCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                        nicknameCheckbtn.isEnabled = true
                        nicknameOk.visibility = View.INVISIBLE
                        nicknameFail.visibility = View.VISIBLE
                    }
                }
            }
        })
        //이메일 중복확인 초기화
        viewmodel.emailText.observe(this@SignupActivity, androidx.lifecycle.Observer {
            binding.apply {
                emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                emailOk.visibility = View.INVISIBLE
                emailFail.visibility = View.INVISIBLE
            }
        })
        //닉네임 중복확인 초기화
        viewmodel.nicknameText.observe(this@SignupActivity, androidx.lifecycle.Observer {
            binding.apply {
                nicknameCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                nicknameOk.visibility = View.INVISIBLE
                nicknameFail.visibility = View.VISIBLE
            }
        })
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

        //회원가입 성공 -> 회원가입 성공화면으로
        viewmodel.requestOk.observe(this@SignupActivity, androidx.lifecycle.Observer {
            it.getIfEvented()?.let {
                Intent(this@SignupActivity,SignupFinActivity::class.java).apply {
                    startActivity(this)
                }
                Log.d(" SignupActivity","just did viewmodel.success.observe")
            }
        })
        //회원가입 실패 -> ?
    }
}