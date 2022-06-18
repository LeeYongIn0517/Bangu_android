package com.example.bangu.main.mybangu.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.bangu.R
import com.example.bangu.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {
    private lateinit var binding: FragmentReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = ReviewViewModel()

        /*영화작품 검색팝업 띄우기*/
        binding.mybanguPlus.setOnClickListener {
            childFragmentManager.beginTransaction(). replace(R.id.search_popup_frame, SearchPuFragment()).commit()
        }
        /*mybangu 디폴트 페이지로 돌아가기*/
        binding.backCursor.setOnClickListener {
            parentFragmentManager.beginTransaction(). replace(R.id.search_popup_frame, MyBanguFragment()).commit()
        }
    }
    /*SearchPuFragment에서 넘어온 값을 수신받을 시점*/
    override fun onResume() {
        super.onResume()
        Log.d("ReviewFragment","onResume called")

        childFragmentManager.setFragmentResultListener("requestKey",viewLifecycleOwner,
            FragmentResultListener{ key,bundle->
                var result_title : String ?= null
                var result_imageUrl : String ?= null
                result_title = bundle.getString("title")
                result_imageUrl = bundle.getString("imageUrl")

                /*수신받은 영화 이미지, 작품명 바인딩*/
                binding.dashImage.visibility = View.INVISIBLE //하연 점선 테두리 없애기
                Glide.with(binding.root).load(result_imageUrl).override(Target.SIZE_ORIGINAL)
                    .into(binding.mybanguImage) //이미지
                binding.resultMovietitle.text = result_title //작품명
            })
    }
}