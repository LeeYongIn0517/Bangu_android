package com.example.bangu.main.profile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.bangu.R
import com.example.bangu.databinding.FragmentProfileBinding
import com.example.bangu.main.profile.presentation.ProfileViewModel
import io.reactivex.disposables.CompositeDisposable

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val disposables =  CompositeDisposable()

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
        viewmodel.apply {
            /**사용자 조회*/
            requestUser(disposables)
            /**팔로워 조회*/
            requestFollower(0,1,disposables)
            /**팔로잉 조회*/
            viewmodel.requestFollowing(0,1,disposables)
            /**북마크 조회*/
            viewmodel.requestBookmark(0,1,disposables)
        }

        /**프로필 아이콘을 누를 경우 기기 기본 갤러리 접근*/
        binding.profilePhoto.setOnClickListener {
            //viewmodel.checkSelfPermission()
        }

        viewmodel.apply {
            /**사용자 이름 바인딩*/
            username.observe(viewLifecycleOwner, Observer {
                binding.userName.text = it
            })
            /**팔로워 수 바인딩*/
            followerNum.observe(viewLifecycleOwner, Observer {
                binding.followerNum.text = it
            })
            /**팔로잉 수 바인딩*/
            followingNum.observe(viewLifecycleOwner, Observer {
                binding.followingNum.text = it
            })
            /**북마크 수 바인딩*/
            bookmarkNum.observe(viewLifecycleOwner, Observer {
                binding.bookmarkNum.text = it
            })
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

    override fun onStop() {
        super.onStop()
        /**관리하고 있던 디스포저블 객체를 모두 해제*/
        disposables.clear()
    }
}