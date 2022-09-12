package com.example.bangu.main.profile.data.model

import com.google.gson.annotations.SerializedName

data class FollowerResponse(
    @SerializedName("followData")
    var followData:UserFollowData,
    @SerializedName("followers")
    var followers:Int,
)
