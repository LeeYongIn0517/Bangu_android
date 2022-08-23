package com.example.bangu.main.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bangu.R
import com.example.bangu.databinding.FragmentProfileRootBinding

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
        /**ProfileFragment 네비게이션*/
        ProfileFragment().apply {
            /**설정페이지*/
            toSetting.observe(viewLifecycleOwner, Observer {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, SettingFragment())
                    commit()
                }
            })
            /**팔로워페이지*/
            toFollower.observe(viewLifecycleOwner, Observer {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, FollowerFragment())
                    commit()
                }
            })
            /**팔로잉페이지*/
            toFollowing.observe(viewLifecycleOwner, Observer {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, FollowingFragment())
                    commit()
                }
            })
            /**북마크페이지*/
            toBookmark.observe(viewLifecycleOwner, Observer {
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, BookmarkFragment())
                    commit()
                }
            })
        }
        return view
    }
}