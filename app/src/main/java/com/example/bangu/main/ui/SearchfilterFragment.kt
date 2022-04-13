package com.example.bangu.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bangu.databinding.FragmentSearchfilterBinding

class SearchfilterFragment:Fragment() {
    private lateinit var binding: FragmentSearchfilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 홈 프레그먼트 보여주기
        binding = FragmentSearchfilterBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }

    override fun onStart(){
        super.onStart()
        //버튼 selected or not 구분하기(우선 netflix만)
        var sign:Boolean
        val netflix = binding.ottNetflixBtn
        val tving = binding.ottTvingBtn
        val watcha = binding.ottWatchaBtn
        val wavve = binding.ottWavveBtn
        val drama = binding.genreDramaBtn
        val movie = binding.genreMovieBtn
        val tvshow = binding.genreTvshowBtn
        val docu = binding.genreDocuBtn

        netflix.setOnClickListener {
            sign = netflix.isSelected
            netflix.isSelected = !sign
        }
        tving.setOnClickListener {
            sign = tving.isSelected
            tving.isSelected = !sign
        }
        watcha.setOnClickListener {
            sign =watcha.isSelected
            watcha.isSelected = !sign
        }
        wavve.setOnClickListener {
            sign =wavve.isSelected
            wavve.isSelected = !sign
        }
        drama.setOnClickListener {
            sign =drama.isSelected
            drama.isSelected = !sign
        }
        movie.setOnClickListener {
            sign =movie.isSelected
            movie.isSelected = !sign
        }
        tvshow.setOnClickListener {
            sign =tvshow.isSelected
            tvshow.isSelected = !sign
        }
        docu.setOnClickListener {
            sign =docu.isSelected
            docu.isSelected = !sign
        }
    }
}