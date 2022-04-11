package com.example.bangu.signup_fin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.signup_fin.data.SgFinRepository
import com.example.bangu.signup_fin.data.model.Content
import com.example.bangu.signup_fin.data.model.SgFinMovieList

class SgFinViewModel: ViewModel() {
    private val repo = SgFinRepository
    private var _contentList = MutableLiveData<List<Content>>()
    val contentList:LiveData<List<Content>> = _contentList
    private val ITEMS_SIZE = 3
    //영화리스트 요청
    fun requestSgFinMovieList(page:Int,size:Int){
        Log.d("SgFinVM","requestSgFinMovieList")
        repo.requestSgFinMovieList(page,ITEMS_SIZE,object:SgFinRepository.GetDataCallback<SgFinMovieList>{
            override fun onSuccess(data: SgFinMovieList?) {
                if (data != null) {
                    _contentList.value = data.content
                }
            }
            override fun onFailure(throwable: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    //영화리스트에서
}