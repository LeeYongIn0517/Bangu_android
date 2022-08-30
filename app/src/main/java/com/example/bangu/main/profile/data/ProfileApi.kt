package com.example.bangu.main.profile.data

import io.reactivex.Single
import retrofit2.http.*

interface ProfileApi {

    @PATCH("/users/update/password")
    fun updatePassword(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("nickname") nickname:String,
    ):Single<Any>

    @PATCH("/users/update/nickname")
    fun updateNickname(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("nickname") nickname:String,
    ):Single<Any>
}