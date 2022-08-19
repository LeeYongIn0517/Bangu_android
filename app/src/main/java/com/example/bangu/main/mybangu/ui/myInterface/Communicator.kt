package com.example.bangu.main.mybangu.ui.myInterface

import com.example.bangu.main.data.model.MovieOtts
import com.example.bangu.main.data.model.MovieResponseData
import java.util.*

interface Communicator {
    fun passData(title:String, imageUrl:String, ott:List<MovieOtts>)
    fun <T> passWholeData(data:T)
}