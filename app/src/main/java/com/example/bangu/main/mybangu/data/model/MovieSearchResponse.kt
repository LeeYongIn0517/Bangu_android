package com.example.bangu.main.mybangu.data.model

import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.data.model.Page
import com.example.bangu.main.data.model.Sort
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    /*속성 값의 타입은 main.data의 데이터클래스를 그대로 사용함*/
    @SerializedName("content")
    var content:List<MovieResponseData>,
    @SerializedName("pageable")
    var pageable: Page,
    @SerializedName("totalElements")
    var totalElements:Int,
    @SerializedName("totalPages")
    var totalPages:Int,
    @SerializedName("last")
    var last:Boolean,
    @SerializedName("number")
    var number:Int,
    @SerializedName("sort")
    var sort: Sort,
    @SerializedName("size")
    var size:Int,
    @SerializedName("numberOfElements")
    var numberOfElements:Int,
    @SerializedName("first")
    var first:Boolean,
    @SerializedName("empty")
    var empty:Boolean
)