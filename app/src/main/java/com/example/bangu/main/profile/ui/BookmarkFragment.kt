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
import com.example.bangu.databinding.FragmentBookmarkBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.profile.presentation.BookmarkViewModel
import io.reactivex.disposables.CompositeDisposable

class BookmarkFragment: Fragment() {
    private lateinit var binding: FragmentBookmarkBinding
    private var page = 0
    private val ITEMS_SIZE = 10
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater,container,false)

        /**백 버튼*/
        binding.btnBack.setOnClickListener{
            //프로필 페이지로 돌아가기
            parentFragmentManager.beginTransaction(). replace(R.id.profile_root_frag, ProfileFragment()).commit()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel = BookmarkViewModel()
        val adapter = BookmarkAdapter()

        /**어댑터 등록*/
        binding.recyclerview.adapter = adapter
        /**서버에서 데이터 초기요청 1번*/
        if(page == 0) viewmodel.requestBookmark(page,ITEMS_SIZE,disposables)
        /**스크롤 리스너*/
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                //스크롤이 끝에 도달했는지 확인
                if(!binding.recyclerview.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount){
                    viewmodel.requestBookmark(++page,ITEMS_SIZE,disposables)
                }
            }
        })
        /**서버에서 온 데이터를 관찰하는 옵저버*/
        viewmodel.reviewList.observe(viewLifecycleOwner, Observer {
            adapter.setList(it as MutableList<Content>)
            adapter.notifyItemInserted(page*ITEMS_SIZE)
        })
        /**어댑터의 언팔로우 유무를 관찰하는 옵저버*/
        adapter.unfollow.observe(viewLifecycleOwner, Observer {
            viewmodel.requestToUnFollow(it.peekContent(),disposables)
        })
        /**어댑터의 팔로우 유무를 관찰하는 옵저버*/
        adapter.follow.observe(viewLifecycleOwner, Observer {
            viewmodel.requestToFollow(it.peekContent(),disposables)
        })
    }
    override fun onStop() {
        super.onStop()
        //관리하고 있던 디스포저블 객체를 모두 해제
        disposables.clear()
    }
}