package com.example.bangu.main.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bangu.main.home.ui.HomeRvFragment

private const val NUM_PAGES = 1
class ScreenSlidePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun createFragment(position: Int): Fragment = HomeRvFragment()

    override fun getItemCount(): Int = NUM_PAGES
}