package com.example.bangu.signup.data

import com.example.bangu.signup.data.model.SignupModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Timestamp

object SgDataResource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://3.34.255.216:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val signupApi = retrofit.create(SignupAPI::class.java)

    fun requestSignup(birth:String,create_at:Timestamp, email:String,gender:Char,netflix:Boolean,nickname:String,
                      password:String,tving:Boolean,update_at:Timestamp,watcha:Boolean, wavve:Boolean,callback: SgRepository.GetDataCallback<SignupModel>){
        signupApi.requestSignup(birth,create_at, email,gender,netflix,nickname,password,tving,update_at,watcha, wavve).enqueue(object:Callback<SignupModel>{
            override fun onResponse(call: Call<SignupModel>, response: Response<SignupModel>) {
                if(response.isSuccessful){
                    callback.onSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<SignupModel>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}