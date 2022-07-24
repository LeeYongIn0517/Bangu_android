package com.example.bangu.main.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.bangu.R
import com.example.bangu.databinding.ActivityMainBinding
import com.example.bangu.main.mybangu.ui.ReviewFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tablayout = binding.mainBtmbar

        //ViewPager2 등록
        viewPager = binding.viewPager
        val sspAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = sspAdapter

        TabLayoutMediator(tablayout,viewPager, TabLayoutMediator.TabConfigurationStrategy{tab: TabLayout.Tab, position: Int ->
            when(position){
                0->tab.apply {
                    setIcon(R.drawable.main_home_states)
                    setText("홈")}
                1->tab.apply {
                    setIcon(R.drawable.main_mybangu_states)
                    setText("내 방구석")}
                2->tab.apply{
                    setIcon(R.drawable.main_feed_states)
                    setText("피드")}
                3->tab.apply {
                    setIcon(R.drawable.main_profile_states)
                    setText("프로필")}
            }
        }).attach()

    }
    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}