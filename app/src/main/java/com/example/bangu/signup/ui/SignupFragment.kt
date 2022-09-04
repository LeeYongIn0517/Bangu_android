package com.example.bangu.signup.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bangu.R
import com.example.bangu.databinding.FragmentSignupBinding
import com.example.bangu.main.mybangu.ui.WarningDialog
import com.example.bangu.signup_fin.ui.SgFinFragment

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var nickname:String
    private lateinit var gender:String
    private var ott = mutableMapOf("tving" to false, "watcha" to false, "netflix" to false, "wavve" to false, "nothing" to false)
    private lateinit var year:String
    private lateinit var month:String
    private lateinit var day:String
    var rdbtn_sign = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel = SignupViewModel()

        binding.apply {
            lifecycleOwner = this@SignupFragment
        }
        //생년월일 스피너 핸들러 등록
        setupSpinnerHandler()
        setupSpinnerYear()
        setupSpinnerMonth()
        setupSpinnerDay()
        //아이디(이메일) 변화 확인
        binding.signupUserId.addTextChangedListener(object :TextWatcher{
            lateinit var previousText:String
            //아이디 확인란에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                previousText = binding.signupUserId.text.toString()
            }
            //아이디 확인란에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            //아이디 확인란에 문자 입력 후
            override fun afterTextChanged(p0: Editable?){
                if(!binding.signupUserId.text.toString().equals(previousText)){ //입력한 아이디에 변화가 생긴경우
                    binding.apply{
                        emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                        emailCheckbtn.isEnabled = true
                        emailOk.visibility = View.INVISIBLE
                        emailFail.visibility = View.INVISIBLE
                    }
                }
            }
        })
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
        //닉네임 변화 확인
        binding.signupNickname.addTextChangedListener(object :TextWatcher{
            lateinit var previousText:String
            //아이디 확인란에 문자 입력 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                previousText = binding.signupNickname.text.toString()
            }
            //아이디 확인란에 변화가 있을 경우
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            //아이디 확인란에 문자 입력 후
            override fun afterTextChanged(p0: Editable?){
                if(!binding.signupNickname.text.toString().equals(previousText)){ //입력한 아이디에 변화가 생긴경우
                    binding.apply{
                        nicknameCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                        nicknameCheckbtn.isEnabled = true
                        nicknameOk.visibility = View.INVISIBLE
                        nicknameFail.visibility = View.INVISIBLE
                    }
                }
            }
        })
        if( binding.rdbtnTving.isSelected.equals(true) || binding.rdbtnWatcha.isSelected.equals(true) ||
            binding.rdbtnNetflix.isSelected.equals(true) || binding.rdbtnWavve.isSelected.equals(true) )
        {   //ott가 체크되면 해당사항 없음을 clear
            binding.rdbtnNothing.isSelected = false
        }
        /*ott 정보 모순 방지하기{ex) 해당사항 없음과 netflix를 같이 선택하지 않게 }*/
        binding.apply {
            rdbtnTving.setOnClickListener {
                rdbtn_sign = it.isSelected
                it.isSelected = !rdbtn_sign
                //정보모순 방지
                rdbtnNothing.isSelected = false
            }
            rdbtnWatcha.setOnClickListener {
                rdbtn_sign = it.isSelected
                it.isSelected = !rdbtn_sign
                //정보모순 방지
                rdbtnNothing.isSelected = false
            }
            rdbtnNetflix.setOnClickListener {
                rdbtn_sign = it.isSelected
                it.isSelected = !rdbtn_sign
                //정보모순 방지
                rdbtnNothing.isSelected = false
            }
            rdbtnWavve.setOnClickListener {
                rdbtn_sign = it.isSelected
                it.isSelected = !rdbtn_sign
                //정보모순 방지
                rdbtnNothing.isSelected = false
            }
            rdbtnNothing.setOnClickListener {
                rdbtn_sign = it.isSelected
                it.isSelected = !rdbtn_sign
                //정보 모순 방지
                rdbtnTving.isSelected = false
                rdbtnWatcha.isSelected = false
                rdbtnNetflix.isSelected = false
                rdbtnWavve.isSelected = false
            }
        }
        //이메일 중복체크 버튼
        binding.emailCheckbtn.setOnClickListener {
            viewmodel.checkUserId(binding.signupUserId.text.toString())
        }
        //닉네임 중복체크 버튼
        binding.nicknameCheckbtn.setOnClickListener {
            viewmodel.checkNickname(binding.signupNickname.text.toString())
        }
        //이메일 중복확인 결과 -> UI반영하기
        viewmodel.userIdOk.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                when(it){
                    "userIdOk"->{ //성공
                        Log.d("SignupActivity.userIdOk","userIdOk")
                        binding.apply {
                            emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn2)
                            emailCheckbtn.isEnabled = false
                            emailOk.visibility = View.VISIBLE
                            emailFail.visibility = View.INVISIBLE
                        }
                    }
                    "userIdFail"->{ //실패
                        Log.d("SignupActivity.userIdFail","userIdFail")
                        binding.apply {
                            emailCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                            emailCheckbtn.isEnabled = true
                            emailOk.visibility = View.INVISIBLE
                            emailFail.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
        //닉네임 중복확인 결과 -> UI반영하기
        viewmodel.nicknameOk.observe(viewLifecycleOwner, Observer {
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
        //닉네임 중복확인 초기화 -> UI반영하기
        viewmodel.nicknameText.observe(viewLifecycleOwner, Observer {
            binding.apply {
                nicknameCheckbtn.setBackgroundResource(R.drawable.signup_confirmbtn)
                nicknameCheckbtn.isEnabled = true
                nicknameOk.visibility = View.INVISIBLE
                nicknameFail.visibility = View.VISIBLE
            }
        })
        //회원가입 요청
        binding.signupBtnpic.setOnClickListener{
            //필수 입력 항목 작성검사
            if(
                binding.emailCheckbtn.isEnabled.equals(true) || //아이디 중복검사 통과 안됨
                binding.pwCheckOk.isVisible.equals(View.INVISIBLE)|| //비번확인 통과 안됨
                binding.nicknameCheckbtn.isEnabled.equals(true) || //닉네임 중복검사 통과 안됨
                (binding.rdbtnTving.isSelected.equals(false) &&
                        binding.rdbtnWatcha.isSelected.equals(false) &&
                        binding.rdbtnNetflix.isSelected.equals(false) &&
                        binding.rdbtnWavve.isSelected.equals(false) &&
                        binding.rdbtnNothing.isSelected.equals(false)) || //구독 중인 ott 기입이 하나도 안됨
                (binding.rdbtnMale.isChecked.equals(false) &&
                        binding.rdbtnFemale.isChecked.equals(false))//성별 기입 안됨
                //생년월일 기입 확인
            )
            {   /*회원가입 양식 제출 불가한 경우*/
                this.context?.let { it1 -> WarningDialog().show(it1) } //경고창 띄우기
            }
            else{
                /*회원가입 양식 제출가능*/
                email = binding.signupUserId.text.toString()
                password = binding.signupPw.text.toString()
                nickname = binding.signupNickname.text.toString()
                ott.set("tving",if(binding.rdbtnTving.isSelected) true else false)
                ott.set("watcha",if(binding.rdbtnWatcha.isSelected) true else false)
                ott.set("netflix",if(binding.rdbtnNetflix.isSelected) true else false)
                ott.set("wavve",if(binding.rdbtnWavve.isSelected) true else false)
                gender = if(binding.rdbtnMale.isChecked) "M" else "F"
                var birth = year+month+day
                Log.d(" SignupActivity","viewmodel.requestSignup")
                viewmodel.requestSignup(birth,email,gender,nickname,password,ott) //서버로 제출
            }
        }
//        //테스트 용
//        binding.loginBtnpic.setOnClickListener {
//            Intent(this@SignupActivity, SgFinActivity::class.java).apply {
//                startActivity(this)
//            }
//        }
        //회원가입 성공 -> 회원가입 성공화면으로
        viewmodel.requestOk.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.getContentIfNotHandled()?.let {
                parentFragmentManager.beginTransaction().replace(R.id.singleFrame, SgFinFragment()).commit()
                Log.d(" SignupActivity","just did viewmodel.success.observe")
            }
        })
    }
    private fun setupSpinnerYear(){
        val years = resources.getStringArray(R.array.birthyear)
        //val adapter = ArrayAdapter(SingleActivity().baseContext,R.layout.spinner_list_image,years)
        val adapter = this.context?.let { ArrayAdapter(it,R.layout.image_spinner_list,years) }
        binding.spinnerBYear.adapter = adapter
    }

    private fun setupSpinnerMonth(){
        val months = resources.getStringArray(R.array.birthmonth)
        val adapter = this.context?.let { ArrayAdapter(it,R.layout.image_mini_spinner_list,months) }
        binding.spinnerBMonth.adapter = adapter
    }
    private fun setupSpinnerDay(){
        val days = resources.getStringArray(R.array.birthday)
        val adapter = this.context?.let { ArrayAdapter(it,R.layout.image_mini_spinner_list,days) }
        binding.spinnerBDay.adapter = adapter
    }
    private fun setupSpinnerHandler(){
        binding.spinnerBYear.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                year = binding.spinnerBYear.getItemAtPosition(p2).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        binding.spinnerBMonth.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                month = binding.spinnerBMonth.getItemAtPosition(p2).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        binding.spinnerBDay.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                day = binding.spinnerBDay.getItemAtPosition(p2).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

}