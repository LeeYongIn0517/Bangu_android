package com.example.bangu.signup_fin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.App
import com.example.bangu.databinding.ActivitySignupFinBinding
import com.example.bangu.login.ui.LoginActivity
import com.example.bangu.main.ui.MainActivity
import com.example.bangu.signup_fin.data.model.Content

class SgFinActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupFinBinding
    private var page = 0
    private val ITEMS_SIZE = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupFinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewmodel = SgFinViewModel()
        val adapter = SgFinAdapter()

        //닉네임 지정
        binding.tvNickname.text = App.signup_prefs.sp_nickname
        //리사이클뷰 어댑터 등록
        binding.signupfinRcyleview
            .adapter = adapter
        //서버에서 온 데이터를 관찰하는 옵저버
        viewmodel.contentList.observe(this, Observer {
            adapter.setList(it as MutableList<Content>)
            adapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE) //이해 100% 안됨
        })
        //스크롤 리스너
        binding.signupfinRcyleview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                //스크롤이 끝에 도달했는지 확인
                if(!binding.signupfinRcyleview.canScrollHorizontally(1) && lastVisibleItemPosition == itemTotalCount){
                    adapter.deleteLoading()
                    viewmodel.requestSgFinMovieList(++page,ITEMS_SIZE)
                }
            }
        })
        //로그인화면으로 이동
        binding.signupfinText.setOnClickListener{
            val next = Intent(this, LoginActivity::class.java)
            startActivity(next)
        }
    }
}