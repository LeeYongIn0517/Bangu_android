package com.example.bangu.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bangu.R
import com.example.bangu.databinding.FragmentLoginBinding
import com.example.bangu.main.ui.MainFragment
import com.example.bangu.signup.ui.SignupFragment
import io.reactivex.disposables.CompositeDisposable

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var email:String
    private lateinit var password:String
    internal val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel = LoginViewModel()

        binding.apply {
            lifecycleOwner = this@LoginFragment

            //앱 자체 로그인인증 시작
            loginStartbtn.setOnClickListener{
                email = loginUserId.text.toString()
                password = loginPw.text.toString()
                viewmodel.getLoginToken(email,password,disposables)
            }

            //kako 로그인 버튼 눌렀을 때
            kakaoBtn.setOnClickListener{
                viewmodel.getKakaoAuthCode()
            }
            //회원가입 페이지로
            loginSignUp.setOnClickListener{
                parentFragmentManager.beginTransaction().replace(R.id.singleFrame, SignupFragment()).commit()
            }
        }
//        //테스트용 프리패스
//        binding.loginStartbtn.setOnClickListener{
//            Intent(this@LoginActivity, MainActivity::class.java).apply {
//                startActivity(this)
//            }
//        }
        viewmodel.getTokenOk.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                //로그인 성공->메인 홈화면으로 (일반, kakao, naver 공통)
                if(it == "getTokenOk"){
                    parentFragmentManager.beginTransaction().replace(R.id.singleFrame, MainFragment()).commit()
                }
                //로그인 실패-> 로그인 EditText UI변경
                else if(it == "getTokenFail"){
                    binding.apply {
                        loginUserId.setBackgroundResource(R.drawable.login_input_error)
                        loginPw.setBackgroundResource(R.drawable.login_input_error)
                        tvError.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        //관리하고 있던 디스포저블 객체를 모두 해제
        disposables.clear()
    }
}