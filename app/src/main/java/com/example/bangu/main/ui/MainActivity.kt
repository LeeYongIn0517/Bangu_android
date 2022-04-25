package com.example.bangu.main.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.bangu.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewPager2 등록
        viewPager = binding.viewPager
        val sspAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = sspAdapter
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