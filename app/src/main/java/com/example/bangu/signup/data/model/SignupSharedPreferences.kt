package com.example.bangu.signup.data.model

import android.content.Context
import android.content.SharedPreferences

class SignupSharedPreferences(context: Context) {
    private val prefsFilename = "signup_prefs"
    private val key_birth = "birth"
    private val key_create_at = "key_create_at"
    private val key_userId= "userId"
    private val key_gender = "gender"
    private val key_id = "id"
    private val key_nickname = "nickname"
    private val key_update_at= "update_at"
//    private val key_ottID = "ottID"
//    private val key_ottName = "ottName"
//    private val key_userId = "userId"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename,0)

    var sp_birth: Long
        get() = prefs.getLong(key_birth,0)
        set(value) = prefs.edit().putLong(key_birth,value).apply()
    var sp_create_at:String?
        get() = prefs.getString(key_create_at,"")
        set(value) = prefs.edit().putString(key_create_at,value).apply()
    var sp_userId:String?
        get() = prefs.getString(key_userId,"")
        set(value) = prefs.edit().putString(key_userId,value).apply()
    var sp_gender: String?
        get() = prefs.getString(key_gender,"")
        set(value) = prefs.edit().putString(key_gender,value).apply()
    var sp_id: Int
        get() = prefs.getInt(key_id,0)
        set(value) = prefs.edit().putInt(key_id,value).apply()
    var sp_nickname:String?
        get() = prefs.getString(key_nickname,"")
        set(value) = prefs.edit().putString(key_nickname,value).apply()
    var sp_update_at:String?
        get() = prefs.getString(key_update_at,"")
        set(value) = prefs.edit().putString(key_update_at,value).apply()
//    var userOttResponseData:ArrayList<userOttResponseData>
//    var sp_ottID: Long
//        get() = prefs.getLong(key_ottID,0)
//        set(value) = prefs.edit().putLong(key_ottID,value).apply()
//    var sp_ottName:String?
//        get() = prefs.getString(key_ottName,"")
//        set(value) = prefs.edit().putString(key_ottName,value).apply()
//    var sp_userId: Long
//        get() = prefs.getLong(key_userId,0)
//        set(value) = prefs.edit().putLong(key_userId,value).apply()
}