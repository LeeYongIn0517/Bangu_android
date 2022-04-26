package com.example.bangu.main.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bangu.main.feed.ui.FeedFragment
import com.example.bangu.main.home.ui.HomeFragment
import com.example.bangu.main.mybangu.ui.MyBanguFragment
import com.example.bangu.main.profile.ui.ProfileFragment

private const val NUM_PAGES = 4

class ScreenSlidePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> MyBanguFragment()
            2 -> FeedFragment()
            else -> {ProfileFragment()} //position = 3
        }
    }
    override fun getItemCount(): Int = NUM_PAGES
}