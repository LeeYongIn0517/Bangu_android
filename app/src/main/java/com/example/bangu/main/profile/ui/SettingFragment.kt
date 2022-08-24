package com.example.bangu.main.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bangu.R
import com.example.bangu.databinding.FragmentSettingsBinding

class SettingFragment: Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)

        /**백 버튼*/
        binding.icBack.setOnClickListener{
            //프로필 페이지로 돌아가기
            parentFragmentManager.beginTransaction(). replace(R.id.profile_root_frag, ProfileFragment()).commit()
        }

        binding.apply {
            /**비번 변경으로*/
            pwChanging.setOnClickListener {
                childFragmentManager.beginTransaction().replace(R.id.setting_root_frag, ChangingPwFragment()).addToBackStack(null).commit()
            }
            /**프로필사진 변경으로*/
            profilePhotoChanging.setOnClickListener {

            }
            /**닉네임 변경으로*/
            nicknameChanging.setOnClickListener {
                childFragmentManager.beginTransaction().replace(R.id.setting_root_frag, ChangingNicknameFragment()).addToBackStack(null).commit()
            }
        }

        return binding.root
    }
}