package com.example.bangu.main.mybangu.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.bangu.databinding.DialogEssentialBinding

class WarningDialog {
    lateinit var binding:DialogEssentialBinding//회원가입,리뷰 양식의 경고창
    fun show(context: Context){
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogEssentialBinding.inflate(inflater)
        val build = AlertDialog.Builder(context).setView(binding.root)

        val dialog = build.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogButton.setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }
}