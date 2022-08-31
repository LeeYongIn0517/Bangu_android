package com.example.bangu.main.profile.data.model

import com.example.bangu.main.data.model.Sort
import com.google.gson.annotations.SerializedName

data class FollowingResponse(
    @SerializedName("followData")
    var followData:UserFollowData,
    @SerializedName("followings")
    var followings:Int,
)
data class UserFollowData(
    @SerializedName("content")
    var content:List<FollowContent>,
    @SerializedName("empty")
    var empty:Boolean,
    @SerializedName("first")
    var first:Boolean,
    @SerializedName("last")
    var last:Boolean,
    @SerializedName("number")
    var number:Int,
    @SerializedName("numberOfElements")
    var numberOfElements:Int,
    @SerializedName("pageable")
    var pageable:Page,
    @SerializedName("size")
    var size: Int,
    @SerializedName("sort")
    var sort: Sort,
    @SerializedName("totalElements")
    var totalElements: Int,
    @SerializedName("totalPages")
    var totalPages: Int,
)
data class Page(
    @SerializedName("page")
    var page: Int,
    @SerializedName("size")
    var size: Int,
    @SerializedName("sort")
    var sort: List<String>,
)
data class FollowContent(
    @SerializedName("followState")
    var followState: Boolean,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("loginUser")
    var loginUser: Boolean,
    @SerializedName("nickname")
    var nickname: String,
    @SerializedName("userId")
    var userId: Int,
)
