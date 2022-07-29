package com.example.bangu.main.mybangu.ui

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.main.data.model.MovieOtts
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.mybangu.data.MyBanguAPI
import com.example.bangu.main.mybangu.data.MyBanguDataResource
import com.example.bangu.main.mybangu.data.MyBanguRepository
import com.example.bangu.main.mybangu.data.model.RegisterReview
import com.example.bangu.main.mybangu.data.model.ReviewOtt
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReviewViewModel: ViewModel() {
    private val myBanguService = MyBanguDataResource.MyBanguApi
    private var _dialog = MutableLiveData<Event<String>>()
    val dialog: LiveData<Event<String>> = _dialog

    /*내가 작성한 리뷰 등록 요청*/
    val accessToken = App.token_prefs.accessToken
    fun registerMyReview(attention:String, content:String,dialogue:String,revealed:Boolean,score:Float,movieData:MovieResponseData){
        //요청데이터 클래스 완성
        val registerReview = RegisterReview(
            actor = movieData.actor,
            attention = attention,
            content = content,
            dialogue = dialogue,
            director = movieData.director,
            genre = movieData.genre,
            imageUrl = movieData.imageUrl,
            movieOtts = movieData.movieOtts,
            revealed = revealed,
            reviewOtt = ReviewOtt(
                netflix = true,
                tving = true,
                watcha = true,
                wavve = true
            ),
            score = score,
            title = movieData.title
        )
        if (accessToken != null) {
            myBanguService.registerMyReview(accessToken,registerReview)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _dialog.postValue(Event("review_registered"))
                },{
                    _dialog.postValue(Event("review_unregistered"))
                })
        }
    }
}