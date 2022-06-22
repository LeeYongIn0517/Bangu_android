package com.example.bangu.main.mybangu.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.bangu.databinding.DialogSignupWarnBinding

class WarningDialog {
    lateinit var binding:DialogSignupWarnBinding //회원가입 다이얼로그와 동일하지만 디자인이 똑같아서 재사용
    fun show(context: Context){
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogSignupWarnBinding.inflate(inflater)
        val build = AlertDialog.Builder(context).setView(binding.root)

        val dialog = build.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogButton.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}