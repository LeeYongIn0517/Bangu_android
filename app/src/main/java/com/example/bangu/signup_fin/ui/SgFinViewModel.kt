package com.example.bangu.signup_fin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.Event
import com.example.bangu.signup_fin.data.SgFinAPI
import com.example.bangu.signup_fin.data.SgFinDataResource
import com.example.bangu.signup_fin.data.SgFinRepository
import com.example.bangu.signup_fin.data.model.Content
import com.example.bangu.signup_fin.data.model.SgFinMovieList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SgFinViewModel: ViewModel() {
    private val SgFinService = SgFinDataResource.sgFinApi
    private var _contentList = MutableLiveData<Event<List<Content>>>()
    val contentList:LiveData<Event<List<Content>>> = _contentList

    //영화리스트 요청
    fun requestSgFinMovieList(page:Int,size:Int,disposable: CompositeDisposable){
        Log.d("SgFinVM","requestSgFinMovieList")
        disposable.add(
            SgFinService.requestSgFinMovieList(page,size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("SgFinViewModel","requestSgFinMovieList().success")
                    _contentList.postValue(Event(it.content))
                }){
                    Log.d("SgFinViewModel","requestSgFinMovieList().fail")
                }
        )
    }
}