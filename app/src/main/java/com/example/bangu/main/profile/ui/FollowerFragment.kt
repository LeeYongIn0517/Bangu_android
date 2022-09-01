package com.example.bangu.main.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.R
import com.example.bangu.databinding.FragmentFollowerBinding
import com.example.bangu.main.profile.data.model.FollowContent
import com.example.bangu.main.profile.presentation.FollowerViewModel
import io.reactivex.disposables.CompositeDisposable

class FollowerFragment: Fragment() {
    private lateinit var binding: FragmentFollowerBinding
    private var page = 0
    private val ITEMS_SIZE = 10
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = FollowerViewModel()
        val adapter = FollowerAdapter()

        /**어댑터 등록*/
        binding.recyclerview.adapter = adapter
        /**서버에 데이터 초기요청 1번*/
        if(page == 0) viewmodel.requestFollower(page,ITEMS_SIZE, disposables) /**사용자의 팔로워 조회*/
        /**스크롤 리스너*/
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager) !!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                /**스크롤이 끝에 도달했는지 확인*/
                if(!binding.recyclerview.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount){
                    viewmodel.requestFollower(++page,ITEMS_SIZE,disposables)
                }
            }
        })
        /**서버에서 온 데이터를 관찰하는 옵저버*/
        viewmodel.follower.observe(viewLifecycleOwner, Observer {
            adapter.setList(it as MutableList<FollowContent>)
            adapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE)
        })
        /**백 버튼*/
        binding.btnBack.setOnClickListener{
            //프로필 페이지로 돌아가기
            parentFragmentManager.beginTransaction(). replace(R.id.profile_root_frag, ProfileFragment()).commit()
        }
    }

    override fun onStop() {
        super.onStop()
        //관리하고 있던 디스포저블 객체를 모두 해제
        disposables.clear()
    }
}