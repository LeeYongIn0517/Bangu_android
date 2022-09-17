package com.example.bangu.main.home.ui

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
import com.example.bangu.databinding.FragmentHomeBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.home.presentation.HomeViewModel
import io.reactivex.disposables.CompositeDisposable

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var page = 0
    private var pageMovie = 0
    private val ITEMS_SIZE = 10
    private val TYPE_REVIEW = "home"
    private val sortType = false
    private val disposables = CompositeDisposable()
    private var positionStart = 0 // 삽입/삭제된 아이템의 첫 위치

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 홈 프레그먼트 보여주기
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = HomeViewModel()
        val adapter = HomeAdapter()
        val movieAdapter = MovieAdapter()

        /**어댑터 등록*/
        binding.apply {
            homeRecyclerview.adapter = adapter
            movieRecyclerview.adapter = movieAdapter
        }

        //서버에서 온 데이터를 관찰하는 옵저버
        viewmodel.reviewList.observe(viewLifecycleOwner, Observer {
            adapter.setList(it as MutableList<Content>)
            adapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE)
        })
        //서버에 데이터 초기요청 1번
        if(page == 0){
            viewmodel.requestReviewList(page,ITEMS_SIZE,TYPE_REVIEW)
        }
        //스크롤 리스너
        binding.homeRecyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                //스크롤이 끝에 도달했는지 확인
                if(!binding.homeRecyclerview.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount){
                    viewmodel.requestReviewList(++page, ITEMS_SIZE,TYPE_REVIEW)
                }
            }
        })
        //북마크 변경사항 알리기
        viewmodel.BookMark.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let{
                when(it){
                    "bookmark_register" -> Toast.makeText(this.context,"해당 리뷰를 북마크목록에 추가했습니다.", Toast.LENGTH_SHORT).apply {
                        setGravity(Gravity.CENTER,0,0)
                        show()
                    }
                    "bookmark_cancel" -> Toast.makeText(this.context,"해당 리뷰를 북마크목록에서 삭제했습니다.", Toast.LENGTH_SHORT).apply {
                        setGravity(Gravity.CENTER,0,0)
                        show()
                    }
                }
            }
        })
        /**검색기능*/
        binding.homeIcSearch.setOnClickListener {
            viewmodel.searchMovie(binding.homeSearch.text.toString(),pageMovie, ITEMS_SIZE,disposables)
            viewmodel.searchReviews(binding.homeSearch.text.toString(),sortType,disposables)
        }
        /**리뷰 검색결과 옵저버*/
        viewmodel.search.observe(viewLifecycleOwner, Observer {
            /**리사이클뷰 초기화하고 검색결과 내용으로 바꾸기*/
            adapter.clearList()
            adapter.apply {
                setList(it as MutableList<Content>)
                notifyItemRangeInserted(0,adapter.itemCount)
            }
        })
        /**영화 검색결과 옵저버*/
        viewmodel.movieList.observe(viewLifecycleOwner, Observer {
            /**페이지 타이틀('최근 올라온 리뷰') 지우기*/
            binding.homeTitle.visibility = View.GONE
            /**movie_recyclerview 가시화, 바인딩*/
            binding.movieRecyclerview.visibility = View.VISIBLE
            movieAdapter.setList(it as MutableList<MovieResponseData>)
            movieAdapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE)
        })
        /**어댑터의 언팔로우 유무를 관찰하는 옵저버*/
        adapter.unfollow.observe(viewLifecycleOwner) {
            viewmodel.requestToUnFollow(it.peekContent(), disposables)
        }
        /**어댑터의 팔로우 유무를 관찰하는 옵저버*/
        adapter.follow.observe(viewLifecycleOwner) {
            viewmodel.requestToFollow(it.peekContent(), disposables)
        }
        /**백 버튼 - '최근 올라온 리뷰'화면으로 돌아가기*/
        binding.homeBack.setOnClickListener {
            /**페이지 타이틀('최근 올라온 리뷰') 초기화*/
            binding.homeTitle.visibility = View.VISIBLE
            /**영화 검색결과 리스트 초기화*/
            movieAdapter.clearList()
            binding.movieRecyclerview.visibility = View.GONE
            pageMovie = 0
            /**리사이클뷰 초기화*/
            adapter.clearList()
            /**최근 올라온 리뷰의 데이터를 요청하도록 페이지 초기화*/
            page = 0
            viewmodel.requestReviewList(page,ITEMS_SIZE,TYPE_REVIEW)
        }
    }
    override fun onStop() {
        super.onStop()
        //관리하고 있던 디스포저블 객체를 모두 해제
        disposables.clear()
    }
}

