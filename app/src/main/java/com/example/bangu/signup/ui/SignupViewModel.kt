package com.example.bangu.signup.ui

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.signup.data.SgRepository
import com.example.bangu.signup.data.model.Signup
import com.example.bangu.signup.data.model.SignupModel

class SignupViewModel:ViewModel(){
    private val repo = SgRepository
    private var _success = MutableLiveData<Event<Boolean>>()
    val success: LiveData<Event<Boolean>> = _success
    val userEmail:MutableLiveData<String> = MutableLiveData()

    fun checkUserEmail(emailText:String){
        repo.checkUserEmail(emailText,object :SgRepository.GetDataCallback<Boolean>{
            override fun onSuccess(data: Boolean?) {
                Toast.makeText(SignupActivity(),"사용가능한 아이디입니다.",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(throwable: Throwable) {
                Toast.makeText(SignupActivity(),"사용할 수 없는 아이디입니다.",Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun checkNickname(nickname:String){
        repo.checkUserEmail(nickname,object :SgRepository.GetDataCallback<Boolean>{
            override fun onSuccess(data: Boolean?) {
                Toast.makeText(SignupActivity(),"사용가능한 닉네임입니다.",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(throwable: Throwable) {
                Toast.makeText(SignupActivity(),"사용할 수 없는 닉네임입니다.",Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun requestSignup(birth:Long, createAt: String, email:String, gender:String, nickname:String,
                      password:String, updateAt: String, ott:MutableMap<String,Boolean>){
        val signup = Signup(
            birth = birth,
            createAt = createAt,
            email = email,
            gender = gender,
            netflix = ott.get("netflix"),
            nickname = nickname,
            password = password,
            tving = ott.get("tving"),
            updateAt = updateAt,
            watcha = ott.get("watcha"),
            wavve = ott.get("wavve"),
        )
        repo.requestSignup(signup, object : SgRepository.GetDataCallback<SignupModel>{
            override fun onSuccess(data: SignupModel?){
                //액티비티에 성공신호 주기
                _success.value = Event(true)
                Log.d(" SignupViewModel","override fun onSuccess")
            }

            override fun onFailure(throwable: Throwable){
            //경고창 띄우기
                Log.d(" SignupViewModel","override fun onFailure")
            }
       })
    }
}