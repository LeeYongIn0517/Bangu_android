package com.example.bangu.main.profile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bangu.R
import com.example.bangu.databinding.FragmentProfileBinding
import com.example.bangu.main.profile.presentation.ProfileViewModel

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
        val viewmodel = ProfileViewModel()

        /**fragment_profile 데이터바인딩해서 이벤트 넘겨서 네비게이션 하기*/
        binding.apply {
            /**설정페이지로 이동*/
            icSetting.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, SettingFragment())
                    commit()
                }
            }
            /**팔로워페이지로 이동*/
            spaceFollower.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, FollowerFragment())
                    commit()
                }
            }
            /**팔로잉페이지로 이동*/
            spaceFollowing.setOnClickListener{
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, FollowingFragment())
                    commit()
                }
            }
            /**북마크페이지로 이동*/
            spaceBookmark.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.profile_root_frag, BookmarkFragment())
                    commit()
                }
            }
        }
        /**프로필 아이콘을 누를 경우 기기 기본 갤러리 접근*/
        binding.profilePhoto.setOnClickListener {
            //viewmodel.checkSelfPermission()
        }
        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}