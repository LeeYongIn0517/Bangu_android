package com.example.bangu.main.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bangu.R
import com.example.bangu.databinding.FragmentMyBanguRootBinding
import com.example.bangu.databinding.FragmentProfileRootBinding
import com.example.bangu.main.mybangu.ui.MyBangu.MyBanguFragment

class ProfileRootFragment: Fragment() {
    private lateinit var binding: FragmentProfileRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileRootBinding.inflate(inflater,container,false)
        val view = binding.root

        /**root프레그먼트를 ProfileFragment로 무조건 교체하고 시작*/
        childFragmentManager.beginTransaction().apply {
            replace(R.id.profile_root_frag, ProfileFragment())
            commit()
        }
        return view
    }
}