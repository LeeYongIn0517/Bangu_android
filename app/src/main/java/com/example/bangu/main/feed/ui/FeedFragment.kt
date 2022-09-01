package com.example.bangu.main.feed.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.databinding.FragmentFeedBinding
import com.example.bangu.main.feed.ui.FeedAdapter
import com.example.bangu.main.feed.ui.FeedViewModel
import io.reactivex.disposables.CompositeDisposable

class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private var page = 0
    private val ITEMS_SIZE = 20
    private val TYPE_REVIEW = "following"
    private val disposables =  CompositeDisposable() //같은 모듈 안에서만 볼 수 있음

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = FeedViewModel()
        val adapter = FeedAdapter()

        /*어댑터 등록*/
        binding.feedRcycleview
            .adapter = adapter
        //서버에 데이터 초기요청 1번
        if(page == 0){
            viewmodel.requestReviewList(page,ITEMS_SIZE,TYPE_REVIEW,disposables)
        }
        //스크롤 리스너
        binding.feedRcycleview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                //스크롤이 끝에 도달했는지 확인
                if(!binding.feedRcycleview.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount){
                    viewmodel.requestReviewList(++page, ITEMS_SIZE,TYPE_REVIEW,disposables)
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        //관리하고 있던 디스포저블 객체를 모두 해제
        disposables.clear()
    }
}