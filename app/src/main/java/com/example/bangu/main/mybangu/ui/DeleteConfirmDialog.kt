package com.example.bangu.main.mybangu.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.core.content.getSystemService
import com.example.bangu.databinding.DialogReviewBinding
import com.example.bangu.databinding.DialogReviewDeleteFinBinding

class DeleteConfirmDialog {
    lateinit var binding:DialogReviewDeleteFinBinding
    fun show(context: Context){
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogReviewDeleteFinBinding.inflate(inflater)
        val build = AlertDialog.Builder(context).setView(binding.root)
        val dialog = build.create()
        var sign:Boolean

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnConfirm.setOnClickListener{
            sign = it.isSelected
            it.isSelected = !sign
            dialog.dismiss()
        }
        dialog.show()
    }
}