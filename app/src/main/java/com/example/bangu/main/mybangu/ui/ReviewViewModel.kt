package com.example.bangu.main.mybangu.ui

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.bangu.App
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
                    //리뷰저장 확인 후 다이얼로그 보여주기
                    ReviewFragment().context?.let { it1 -> ReviewDialog().show(it1, "리뷰가 저장되었습니다!") }
                },{
                    //네트워킹 실패 토스트 보여주기
                    Toast.makeText(ReviewFragment().context,"리뷰 저장에 실패했습니다",Toast.LENGTH_SHORT).show()
                })
        }
    }
}