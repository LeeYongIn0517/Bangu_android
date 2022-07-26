package com.example.bangu.main.mybangu.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.bangu.databinding.DialogEssentialBinding
import com.example.bangu.databinding.DialogReviewBinding

class ReviewDialog {
    lateinit var binding: DialogReviewBinding//리뷰 저장,수정
    fun show(context: Context, message:String){
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogReviewBinding.inflate(inflater)
        val build = AlertDialog.Builder(context).setView(binding.root)

        binding.message.text = message //종류별 메세지 바인딩

        val dialog = build.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogButton.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}