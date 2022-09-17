package com.example.bangu.main.feed.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.databinding.FragmentFeedBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.feed.ui.FeedAdapter
import com.example.bangu.main.feed.ui.FeedViewModel
import io.reactivex.disposables.CompositeDisposable

class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private var page = 0
    private var pageMovie = 0
    private val ITEMS_SIZE = 20
    private val TYPE_REVIEW = "following"
    private val sortType = false
    private val disposables =  CompositeDisposable() //같은 모듈 안에서만 볼 수 있음

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = FeedViewModel()
        val adapter = FeedAdapter()
        val movieAdapter = FeedMovieAdapter()

        /**어댑터 등록*/
        binding.apply {
            feedRecyclerview.adapter = adapter
            feedMovieRecyclerview.adapter = movieAdapter
        }

        /**서버에 데이터 초기요청 1번*/
        if(page == 0){
            viewmodel.requestReviewList(page,ITEMS_SIZE,TYPE_REVIEW,disposables)
        }

        /**리뷰데이터 옵저버*/
        viewmodel.reviewList.observe(viewLifecycleOwner, Observer {
            adapter.setList(it as MutableList<Content>)
            adapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE)
        })

        /**스크롤 리스너*/
        binding.feedRecyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                /**스크롤이 끝에 도달했는지 확인*/
                if(!binding.feedRecyclerview.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount){
                    viewmodel.requestReviewList(++page, ITEMS_SIZE,TYPE_REVIEW,disposables)
                }
            }
        })

        /**검색 기능*/
        binding.feedIcSearch.setOnClickListener {
            viewmodel.searchMovie(binding.feedSearch.text.toString(),pageMovie,ITEMS_SIZE,disposables)
            viewmodel.searchReviews(binding.feedSearch.text.toString(),sortType,disposables)
        }
        /**영화 검색결과 옵저버*/
        viewmodel.movieList.observe(viewLifecycleOwner, Observer {
            /**페이지 타이틀('팔로잉') 지우기*/
            binding.feedTitle.visibility = View.GONE
            /**feed_recyclerview 가시화, 바인딩*/
            binding.feedMovieRecyclerview.visibility = View.VISIBLE
            movieAdapter.setList(it as MutableList<MovieResponseData>)
            movieAdapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE)
        })
        /**리뷰 검색결과 옵저버*/
        viewmodel.searchReviews.observe(viewLifecycleOwner, Observer {
            /**리사이클뷰 초기화하고 검색결과 내용으로 바꾸기*/
            adapter.clearList()
            adapter.apply {
                setList(it as MutableList<Content>)
                notifyItemRangeInserted(0,adapter.itemCount)
            }
        })
        /**북마크 및 북마크해제 옵저버*/
        adapter.BookMark.observe(viewLifecycleOwner, Observer {
            viewmodel.adjustBookmark(it.peekContent(),disposables)
        })
        /**백 버튼 - '팔로잉' 화면으로 돌아가기*/
        binding.feedBack.setOnClickListener {
            /**페이지 타이틀('팔로잉') 초기화*/
            binding.feedTitle.visibility = View.VISIBLE
            /**영화 검색결과 리스트 초기화*/
            movieAdapter.clearList()
            binding.feedMovieRecyclerview.visibility = View.GONE
            pageMovie = 0
            /**리사이클뷰 초기화*/
            adapter.clearList()
            /**최근 올라온 리뷰의 데이터를 요청하도록 페이지 초기화*/
            page = 0
            viewmodel.requestReviewList(page, ITEMS_SIZE,TYPE_REVIEW,disposables)
        }
    }

    override fun onStop() {
        super.onStop()
        /**관리하고 있던 디스포저블 객체를 모두 해제*/
        disposables.clear()
    }
}