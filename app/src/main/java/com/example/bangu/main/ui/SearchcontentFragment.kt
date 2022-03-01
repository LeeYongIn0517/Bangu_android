package com.example.bangu.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bangu.R
import com.example.bangu.databinding.FragmentSearchcontentBinding
import com.example.bangu.databinding.FragmentSearchfilterBinding

class SearchcontentFragment : Fragment() {
    private lateinit var binding:FragmentSearchcontentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchcontentBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }
}