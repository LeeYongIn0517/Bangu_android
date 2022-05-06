package com.example.bangu.main.mybangu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.R
import com.example.bangu.databinding.FragmentHomeBinding
import com.example.bangu.databinding.FragmentMyBanguBinding
import com.example.bangu.main.home.ui.HomeAdapter

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
        val adapter = HomeAdapter()

        /*어댑터 등록*/
        binding.mybanguRcycleview
            .adapter = adapter
        /*서버에서 데이터 초기요청 1번*/
        if(page == 0){

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
    }
}