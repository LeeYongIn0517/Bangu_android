package com.example.bangu.signup.data

import com.example.bangu.signup.data.model.SignupModel
import java.sql.Timestamp

object SgRepository {
    private val remoteDataService = SgDataResource
    fun requestSignup(birth:String, create_at: Timestamp, email:String, gender:Char, netflix:Boolean, nickname:String,
                      password:String, tving:Boolean, update_at: Timestamp, watcha:Boolean, wavve:Boolean, callback:GetDataCallback<SignupModel>){
        remoteDataService.requestSignup(birth,create_at, email,gender,netflix,nickname,password,tving,update_at,watcha, wavve,callback)
    }
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}