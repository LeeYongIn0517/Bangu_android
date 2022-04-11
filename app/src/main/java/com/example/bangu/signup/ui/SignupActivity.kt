package com.example.bangu.signup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.bangu.R
import com.example.bangu.databinding.ActivitySignupBinding
import com.example.bangu.signup_fin.ui.SgFinActivity

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
        //비밀번호 확인
        binding.signupCheck.addTextChangedListener(object : TextWatcher{
            //비밀번호 확인란에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            //비밀번호 확인란에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            //비밀번호 확인란에 문자 입력 후
            override fun afterTextChanged(p0: Editable?) {
                //일치
                if(binding.signupCheck.text.toString().equals(binding.signupPw.text.toString())){
                    binding.pwCheckOk.visibility = View.VISIBLE
                    binding.pwCheckFail.visibility = View.INVISIBLE
                }
                //불일치
                else{
                    binding.pwCheckOk.visibility = View.INVISIBLE
                    binding.pwCheckFail.visibility = View.VISIBLE
                }
            }
        })
        //이메일 중복체크 버튼
        binding.emailCheckbtn.setOnClickListener {
                viewmodel.checkUserEmail(binding.signupEmail.text.toString())
        }
        //닉네임 중복체크 버튼
        binding.nicknameCheckbtn.setOnClickListener {
                viewmodel.checkNickname(binding.signupNickname.text.toString())
        }
        //이메일 중복확인 결과 -> UI반영하기
        viewmodel.emailOk.observe(this@SignupActivity, androidx.lifecycle.Observer {
            it.getContentIfNotHandled()?.let {
                if(it == "emailOk"){
                    //성공
                    Log.d("SignupActivity.emailOk","emailOk")
                    binding.apply {
                        emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn2)
                        emailCheckbtn.isEnabled = false
                        emailOk.visibility = View.VISIBLE
                        emailFail.visibility = View.INVISIBLE
                    }
                }
                else if(it == "emailFail"){
                    //실패
                    Log.d("SignupActivity.emailFail","emailFail")
                    binding.apply {
                        emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                        emailCheckbtn.isEnabled = true
                        emailOk.visibility = View.INVISIBLE
                        emailFail.visibility = View.VISIBLE
                    }
                }
            }
        })
        //닉네임 중복확인 결과 -> UI반영하기
        viewmodel.nicknameOk.observe(this@SignupActivity, androidx.lifecycle.Observer {
            it.getContentIfNotHandled()?.let {
                if(it == "nicknameOk"){
                    //성공
                    Log.d("SignupActivity.nicknameOk","nicknameOk")
                    binding.apply {
                        nicknameCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn2)
                        nicknameCheckbtn.isEnabled = false
                        nicknameOk.visibility = View.VISIBLE
                        nicknameFail.visibility = View.INVISIBLE
                    }
                }
                else if(it == "nicknameFail"){
                    //실패
                    Log.d("SignupActivity.nicknameFail","nicknameFail")
                    binding.apply {
                        nicknameCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                        nicknameCheckbtn.isEnabled = true
                        nicknameOk.visibility = View.INVISIBLE
                        nicknameFail.visibility = View.VISIBLE
                    }
                }
            }
        })
        //이메일 중복확인 초기화 -> UI반영하기
        viewmodel.emailText.observe(this@SignupActivity, androidx.lifecycle.Observer {
            binding.apply {
                emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                emailCheckbtn.isEnabled = true
                emailOk.visibility = View.INVISIBLE
                emailFail.visibility = View.INVISIBLE
            }
        })
        //닉네임 중복확인 초기화 -> UI반영하기
        viewmodel.nicknameText.observe(this@SignupActivity, androidx.lifecycle.Observer {
            binding.apply {
                nicknameCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                nicknameCheckbtn.isEnabled = true
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
            Log.d(" SignupActivity","viewmodel.requestSignup")
            viewmodel.requestSignup(birth,email,gender,nickname,password,ott)
        }
//        //테스트 용
//        binding.loginBtnpic.setOnClickListener {
//            Intent(this@SignupActivity, SignupFinActivity::class.java).apply {
//                startActivity(this)
//            }
//        }
        //회원가입 성공 -> 회원가입 성공화면으로
        viewmodel.requestOk.observe(this@SignupActivity, androidx.lifecycle.Observer {
            it.getContentIfNotHandled()?.let {
                Intent(this@SignupActivity, SgFinActivity::class.java).apply {
                    startActivity(this)
                }
                Log.d(" SignupActivity","just did viewmodel.success.observe")
            }
        })
        //회원가입 실패 -> ?
    }
}