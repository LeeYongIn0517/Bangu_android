package com.example.bangu.main.profile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bangu.R
import com.example.bangu.databinding.FragmentProfileBinding
import com.example.bangu.main.mybangu.ui.MyBangu.MyBanguFragment

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        val view = binding.root
        /**개별페이지로 이동*/
        binding.apply {
            /**설정 페이지*/
            icSetting.setOnClickListener{
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, SettingFragment())
                    commit()
                }
            }
            /**팔로워 페이지*/
            spaceFollower.setOnClickListener{
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, FollowerFragment())
                    commit()
                }
            }
            /**팔로잉 페이지*/
            spaceFollowing.setOnClickListener{
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, FollowingFragment())
                    commit()
                }
            }
            /**북마크 페이지*/
            spaceBookmark.setOnClickListener{
                childFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, BookmarkFragment())
                    commit()
                }
            }
        }

        return view
    }
}