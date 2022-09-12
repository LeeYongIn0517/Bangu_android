package com.example.bangu.main.profile.data

import com.example.bangu.main.data.MainAPI
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ProfileDataResource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bangu.shop:443")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    internal val ProfileApi = retrofit.create(ProfileApi::class.java)
}