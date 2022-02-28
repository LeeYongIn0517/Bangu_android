package com.example.bangu.main.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bangu.R
import com.example.bangu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val myHandler = Handler(Looper.getMainLooper())

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
        binding.homeRcycleview
            .adapter = HomeAdapter()
    }

    override fun onStart() {
        val workThread = Thread(HeightControl(550))
        val workThread2 = Thread(HeightControl(0))
        super.onStart()
        binding.searchBar.setOnFocusChangeListener { view, b ->
            if (b) {
                //검색필터 프레그먼트 호출하기
                val fragmentManager = childFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = SearchfilterFragment()
                fragmentTransaction.add(R.id.frame_filter, fragment)
                fragmentTransaction.commit()
                //검색필터 펼치기
                workThread.start()
            }
            else{
                //검색필터 접기
                workThread2.start()
            }
        }
    }
    inner class HeightControl(i:Int):Runnable{
        val h = i
        override fun run() {
            myHandler.post {
                //필터 프레그먼트 화면 늘리기
                binding.frameFilter.layoutParams.height = h
                binding.frameFilter.invalidate()
            }
        }
    }
}

