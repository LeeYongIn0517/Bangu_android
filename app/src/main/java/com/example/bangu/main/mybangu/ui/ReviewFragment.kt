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
import com.example.bangu.main.data.model.MovieOtts

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
                val ottList:List<MovieOtts> = bundle.get("ott") as List<MovieOtts>

                /*수신받은 영화 이미지, 작품명, ott 바인딩*/
                binding.dashImage.visibility = View.INVISIBLE // 하얀 점선 테두리 없애기
                Glide.with(binding.root).load(bundle.getString("imageUrl")).override(Target.SIZE_ORIGINAL)
                    .into(binding.mybanguImage) //이미지
                binding.resultMovietitle.text = bundle.getString("title") //작품명

                val ottSize = ottList.size
                binding.apply{ //ott아이콘 초기화
                    reviewNetflix.visibility = View.GONE
                    reviewTving.visibility = View.GONE
                    reviewWatcha.visibility = View.GONE
                    reviewWavve.visibility = View.GONE
                }
                for(i in 0 until ottSize!!){
                    when(ottList.get(i).ottName){
                        "NETFLIX" -> binding.reviewNetflix.visibility = View.VISIBLE
                        "TVING" -> binding.reviewTving.visibility = View.VISIBLE
                        "WATCHAPLAY" -> binding.reviewWatcha.visibility = View.VISIBLE
                        "WAVVE" -> binding.reviewWavve.visibility = View.VISIBLE
                    }
                }
            })
    }
}