package com.example.bangu.main.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bangu.databinding.FragmentChangingNicknameBinding

class ChangingNicknameFragment:Fragment() {
    private lateinit var binding: FragmentChangingNicknameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangingNicknameBinding.inflate(inflater,container,false)

        /**백 버튼 - 돌아가기*/
        binding.icBack.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
        return binding.root
    }
}