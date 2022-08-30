package com.example.bangu.main.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bangu.R
import com.example.bangu.databinding.FragmentUpdatePwBinding
import com.example.bangu.main.profile.presentation.UpdatePwViewmodel
import io.reactivex.disposables.CompositeDisposable

class UpdatePwFragment: Fragment() {
    private lateinit var binding: FragmentUpdatePwBinding
    private val disposables = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdatePwBinding.inflate(inflater,container,false)
        var viewmodel = UpdatePwViewmodel()
        /**백 버튼 - 돌아가기*/
        binding.icBack.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
        /**완료 버튼 - 비밀번호 변경 제출*/
        binding.btnSubmit.setOnClickListener {
            /**비밀번호 조건 검사*/
            when (viewmodel.checkPassword(binding.pwInput.text.toString())) {
                true -> { viewmodel.updatePassword(binding.pwInput.text.toString(),disposables) } //서버로 요청보내기
                false -> {
                    //UI를 빨간색으로 변경해야 하지만 setHintColor가 deprecated되어서 toast로 임시변경.
                    Toast.makeText(this.context,"비밀번호 조건에 맞지 않습니다.",Toast.LENGTH_SHORT).show()
                }
            }
        }

        /**서버 응답을 UI에서 반영하기*/
        /**비밀번호 업데이트 성공*/
        viewmodel.updateOk.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context,"비밀번호 변경을 완료했습니다.",Toast.LENGTH_SHORT).show()
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
