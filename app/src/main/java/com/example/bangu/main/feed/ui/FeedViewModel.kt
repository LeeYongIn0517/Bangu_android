package com.example.bangu.main.feed.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.Event
import com.example.bangu.login.data.LgDataResource
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.feed.data.FeedDataResource
import com.example.bangu.main.feed.data.FeedRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class FeedViewModel: ViewModel() {
    private val ITEMS_SIZE = 20

    private val repo = FeedRepository
    //val adapter = FeedAdapter()

    private val feedService = FeedDataResource.MainApi //레트로빗 객체
    private var _reviewList = MutableLiveData<List<Content>>()
    val reviewList: LiveData<List<Content>> = _reviewList
    private var _BookMark = MutableLiveData<Event<String>>()
    val BookMark: LiveData<Event<String>> = _BookMark

    //팔로잉하는 리뷰어의 리뷰리스트 요청
    val accessToken = App.token_prefs.accessToken
    fun requestReviewList(page:Int, size:Int,type:String,disposables: CompositeDisposable){
        if (accessToken != null) {
            Log.d("accessToken",accessToken)
        }
        Log.d("FeedVM","requestReviewList")

        if (accessToken != null) {
            disposables.add(
                feedService.requestReviewList(accessToken,page,size,type)
                    .flatMap {
                        if(0 == it.totalElements){
                            //검색결과 없을 경우 에러 메세지 표시
                            Observable.error(IllegalStateException("No search result"))
                        }else{
                            //검색 결과 리스트를 다음 스트림으로 전달
                            Observable.just(it.content)
                        }
                    }
                    //이후 수행되는 코드는 메인 스레드에서 실행
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        //구독할 때 수행할 작업구현
                    }
                    .doOnTerminate { /*스트림이 종료될 때 수행할 작업 구현*/ }
                    //옵서버블 구독
                    .subscribe({ item->
                        //검색결과 정상적으로 받았으 ㄹ때
//                        with(adapter){
//                            adapter.setList(item as MutableList<Content>)
//                            adapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE)
//                        }
                    }){
                        //에러 블록
                        //네트워크 오류나 데이터 처리 오류 등
                        //작업이 정상적으로 완료되지 않았을 때 호출됨
                    }
            )
        }

    }
    fun adjustBookmark(reviewId:Int){
        if (accessToken != null) {
            repo.adjustBookmark(accessToken,reviewId)
        }
    }
}