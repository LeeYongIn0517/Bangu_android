package com.example.bangu.main.home.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.R
import com.example.bangu.databinding.FragmentHomeBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.ui.SearchfilterFragment

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val myHandler = Handler(Looper.getMainLooper())
    private var page = 0
    private val ITEMS_SIZE = 10
    private val TYPE_REVIEW = "home"

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

        //어댑터 등록
        binding.homeRecyclerview
            .adapter = adapter
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

    }

}

