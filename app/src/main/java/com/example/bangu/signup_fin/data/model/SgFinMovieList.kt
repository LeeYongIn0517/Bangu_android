package com.example.bangu.signup_fin.data.model

import com.google.gson.annotations.SerializedName

data class SgFinMovieList(
    @SerializedName("content")
    var content:List<Content>,
    @SerializedName("pageable")
    var pageable: Pageable,
    @SerializedName("offset")
    var offset:Int,
    @SerializedName("pageNumber")
    var pageNumber:Int,
    @SerializedName("pageSize")
    var pageSize:Int,
    @SerializedName("paged")
    var paged:Boolean,
    @SerializedName("unpaged")
    var unpaged:Boolean,
    @SerializedName("totalElements")
    var totalElements:Int,
    @SerializedName("totalPages")
    var totalPages:Int,
    @SerializedName("last")
    var last:Boolean,
    @SerializedName("number")
    var number:Int,
    @SerializedName("sort")
    var sort:Sort,
    @SerializedName("size")
    var size:Int,
    @SerializedName("numberOfElements")
    var numberOfElements:Int,
    @SerializedName("first")
    var first:Boolean,
    @SerializedName("empty")
    var empty:Boolean
)
data class Content(
    @SerializedName("movieId")
    var movieId:Int,
    @SerializedName("imageUrl")
    var imageUrl:String,
    @SerializedName("score")
    var score:Float
)
data class Pageable(
    @SerializedName("sort")
    var sort:Sort
)
data class Sort(
    @SerializedName("empty")
    var empty: Boolean,
    @SerializedName("sorted")
    var sorted:Boolean,
    @SerializedName("unsorted")
    var unsorted:Boolean
)