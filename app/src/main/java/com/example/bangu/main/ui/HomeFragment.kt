package com.example.bangu.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bangu.R
import com.example.bangu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 홈 프레그먼트 보여주기
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view=binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRcycleview
            .adapter = HomeAdapter()
    }

    override fun onStart() {
        super.onStart()
        //EditText 누르면
//        binding.searchBar.setOnClickListener{
//            //검색필터 프레그먼트 호출하기
//            val fragmentManager = childFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            val fragment = SearchfilterFragment()
//            fragmentTransaction.add(R.id.frame_filter, fragment)
//            fragmentTransaction.commit()
//        }
        binding.searchBar.setOnFocusChangeListener { view, b ->
            if(b){
                //검색필터 프레그먼트 호출하기
                val fragmentManager = childFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = SearchfilterFragment()
                fragmentTransaction.add(R.id.frame_filter, fragment)
                fragmentTransaction.commit()
            }
        }
    }
}

