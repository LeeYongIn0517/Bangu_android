package com.example.bangu.main.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bangu.R
import com.example.bangu.databinding.FragmentFollowerBinding

class FollowerFragment: Fragment() {
    private lateinit var binding: FragmentFollowerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerBinding.inflate(inflater,container,false)

        /**백 버튼*/
        binding.btnBack.setOnClickListener{
            //프로필 페이지로 돌아가기
            parentFragmentManager.beginTransaction(). replace(R.id.profile_root_frag, ProfileFragment()).commit()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}