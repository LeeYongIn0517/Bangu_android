package com.example.bangu.signup.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.Event
import com.example.bangu.signup.data.SgRepository
import com.example.bangu.signup.data.model.SignupRequest
import com.example.bangu.signup.data.model.SignupResponse

class SignupViewModel:ViewModel(){
    private val repo = SgRepository
    private var _requestOk = MutableLiveData<Event<Boolean>>()
    val requestOk: LiveData<Event<Boolean>> = _requestOk
    private var _emailOk = MutableLiveData<Event<String>>()
    val emailOk: LiveData<Event<String>> = _emailOk
    private var _nicknameOk = MutableLiveData<Event<String>>()
    val nicknameOk: LiveData<Event<String>> = _nicknameOk
    private var _emailText = MutableLiveData<String>() //미완
    val emailText : LiveData<String> = _emailText
    private var _nicknameText = MutableLiveData<String>() //미완
    val nicknameText : LiveData<String> = _nicknameText

    fun checkUserEmail(emailText:String){
        Log.d("SignupVM.checkUserEmail","checkUserEmail")
        repo.checkUserEmail(emailText,object :SgRepository.GetDataCallback<Boolean>{
            override fun onSuccess(data: Boolean?) {
                if (data != null) {
                    //LiveData로 액티비티에 성공신호 제공
                        if(data == true) _emailOk.postValue(Event("emailFail")) //아이디 존재x
                    else if(data == false) _emailOk.postValue(Event("emailOk")) //아이디 이미 존재함(아이디 중복처리 필요)
                }
            }

            override fun onFailure(throwable: Throwable) {
                //실패
                Log.d("SignupVM.checkUserEmail","onFailure")
            }
        })
    }
    fun checkNickname(nickname:String){
        repo.checkNickname(nickname,object :SgRepository.GetDataCallback<Boolean>{
            override fun onSuccess(data: Boolean?) {
                if (data != null) {
                    //LiveData로 액티비티에 성공신호 제공
                        if(data == true) _nicknameOk.postValue(Event("nicknameFail"))
                    else if(data == false) _nicknameOk.postValue(Event("nicknameOk"))
                }
            }

            override fun onFailure(throwable: Throwable) {
                //실패
                Log.d("SignupVM.checkNickname","onFailure")
            }
        })
    }
    //이메일 중복 초기화
    fun emailReset(){

    }
    //닉네임 중복 초기화
    fun NicknameReset(){

    }
    fun requestSignup(birth:Long, email:String, gender:String, nickname:String,
                      password:String,ott:MutableMap<String,Boolean>){
        Log.d("SignupVM.checkNickname","requestSignup")
        val signup = SignupRequest(
            birth = birth,
            email = email,
            gender = gender,
            netflix = ott.get("netflix"),
            nickname = nickname,
            password = password,
            tving = ott.get("tving"),
            watcha = ott.get("watcha"),
            wavve = ott.get("wavve")
        )
        repo.requestSignup(signup, object : SgRepository.GetDataCallback<SignupResponse>{
            override fun onSuccess(data: SignupResponse?){
                if (data != null) {
                    Log.d(" SignupViewModel","override fun onSuccess")
                    //LiveData로 액티비티에 성공신호 제공
                    _requestOk.postValue(Event(true))
                    Log.d(" Test",data.birth.toString()) //확인 용
                }
            }
            override fun onFailure(throwable: Throwable){
            //경고창 띄우기
                Log.d(" SignupViewModel","override fun onFailure")
            }
       })
    }
}