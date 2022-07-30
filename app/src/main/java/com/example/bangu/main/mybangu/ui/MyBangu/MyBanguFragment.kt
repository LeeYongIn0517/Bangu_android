package com.example.bangu.main.mybangu.ui.MyBangu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.R
import com.example.bangu.databinding.FragmentMyBanguBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.mybangu.ui.Review.ReviewFragment

class MyBanguFragment : Fragment() {
    private lateinit var binding: FragmentMyBanguBinding
    private var page = 0
    private val ITEMS_SIZE = 3
    private val TYPE_REVIEW = "myReviews"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBanguBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = MyBanguViewModel()
        val adapter = MyBanguAdapter()

        /*어댑터 등록*/
        binding.mybanguRcycleview
            .adapter = adapter
        /*서버에서 온 데이터를 관찰하는 옵저버*/
        viewmodel.reviewList.observe(viewLifecycleOwner, Observer {
            adapter.setList(it as MutableList<Content>)
            adapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE)
        })
        /*서버에서 데이터 초기요청 1번*/
        if(page == 0){
            viewmodel.requestMyReviews(page,ITEMS_SIZE,TYPE_REVIEW)
        }
        /*스크롤 리스너*/
        binding.mybanguRcycleview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                //스크롤이 끝에 도달했는지 확인
                if(!binding.mybanguRcycleview.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount){
                    adapter.deleteLoading()
                    //viewmodel.requestReviewList(++page, ITEMS_SIZE,TYPE_REVIEW)
                }
            }
        })
        /*리뷰 작성 기능*/
        binding.mybanguWritebtn.setOnClickListener{
            //ReviewFragment로 이동
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mybangu_root_frag, ReviewFragment())
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN) //Fragment is being added onto the stack
                addToBackStack(null)
                commit()
            }
        }
        /*리뷰 수정버튼 이벤트 전달받기*/
        adapter.review.observe(viewLifecycleOwner, Observer {
            //화면 전환하기
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mybangu_root_frag, ReviewFragment())
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN) //Fragment is being added onto the stack
                addToBackStack(null)
                commit()
            }
        })

    }
}