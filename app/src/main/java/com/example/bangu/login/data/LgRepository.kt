package com.example.bangu.login.data

object LgRepository {

    fun requestLogin(){
        val lgdr = LgDataResource
        lgdr.requestLogin() //인자 전달
    }
    //로그인 요청에 대한 응답인터페이스
    interface GetDataCallback<T>{
        fun onSuccess(){}
        fun onFailure(){}
    }
}