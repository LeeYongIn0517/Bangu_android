package com.example.bangu.main.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bangu.databinding.FragmentUpdateNicknameBinding
import com.example.bangu.main.profile.presentation.UpdateNicknameViewmodel
import io.reactivex.disposables.CompositeDisposable

class UpdateNicknameFragment:Fragment() {
    private lateinit var binding: FragmentUpdateNicknameBinding
    private val disposables = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNicknameBinding.inflate(inflater,container,false)
        var viewmodel = UpdateNicknameViewmodel()
        /**백 버튼 - 돌아가기*/
        binding.icBack.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
        /**완료 버튼 - 닉네임 변경 제출*/
        binding.btnSubmit.setOnClickListener {
            viewmodel.updateNickname(binding.nicknameInput.text.toString(),disposables) //서버로 요청보내기
        }

        /**서버 응답을 UI에서 반영하기*/
        /**닉네임 업데이트 성공*/
        viewmodel.updateOk.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context,"닉네임 변경을 완료했습니다.",Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().remove(this).commit()
        })

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        //관리하고 있던 디스포저블 객체를 모두 해제
        disposables.clear()
    }
}