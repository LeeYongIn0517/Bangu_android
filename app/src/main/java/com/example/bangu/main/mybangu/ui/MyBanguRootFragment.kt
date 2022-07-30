package com.example.bangu.main.mybangu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bangu.R
import com.example.bangu.databinding.FragmentMyBanguRootBinding
import com.example.bangu.main.mybangu.ui.MyBangu.MyBanguFragment

class MyBanguRootFragment : Fragment() {
    private lateinit var binding: FragmentMyBanguRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBanguRootBinding.inflate(inflater,container,false)
        val view = binding.root

        /*root프레그먼트를 MyBanguFragment로 무조건 교체하고 시작*/
        childFragmentManager.beginTransaction().apply {
            replace(R.id.mybangu_root_frag, MyBanguFragment())
            commit()
        }
        return view
    }
}