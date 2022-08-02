package com.example.bangu.main.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bangu.main.mybangu.ui.MyBanguRootFragment
import com.example.bangu.main.feed.ui.FeedFragment
import com.example.bangu.main.home.ui.HomeFragment
import com.example.bangu.main.profile.ui.ProfileFragment

private const val NUM_PAGES = 4

class ScreenSlidePagerAdapter(f: Fragment): FragmentStateAdapter(f){
    val fragmentList = mutableListOf(HomeFragment(),
        MyBanguRootFragment(), FeedFragment(),ProfileFragment())
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int = NUM_PAGES

}