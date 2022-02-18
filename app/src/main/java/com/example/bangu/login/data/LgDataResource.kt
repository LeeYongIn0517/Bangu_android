package com.example.bangu.login.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LgDataResource {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://") //도메인 주소
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun requestLogin(){

    }

}