package com.example.bangu.main.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.bangu.App
import com.example.bangu.R
import com.example.bangu.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tablayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

//    override fun onBackPressed() {
//        if (viewPager.currentItem == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed()
//        } else {
//            // Otherwise, select the previous step.
//            viewPager.currentItem = viewPager.currentItem - 1
//        }
//    }
}