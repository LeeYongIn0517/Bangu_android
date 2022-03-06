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
    fun requestSignup(birth:Long, createAt:String, email:String, gender:String, nickname:String,
                      password:String, updateAt:String, ott:MutableMap<String,Boolean>,callback: SgRepository.GetDataCallback<SignupModel>){
        var tving = ott.get("tving")
        var netflix = ott.get("netflix")
        var watcha = ott.get("watcha")
        var wavve = ott.get("wavve")
        signupApi.requestSignup(birth,createAt, email,gender,netflix,nickname,password,tving,updateAt,watcha,wavve).enqueue(object:Callback<SignupModel>{
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