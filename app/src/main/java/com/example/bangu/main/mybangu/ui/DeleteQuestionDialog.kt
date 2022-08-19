package com.example.bangu.main.mybangu.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import com.example.bangu.databinding.DialogReviewDeleteQBinding
import com.example.bangu.main.mybangu.ui.myInterface.DialogListener

class DeleteQuestionDialog {
    lateinit var binding:DialogReviewDeleteQBinding

    fun show(context: Context,listener:DialogListener){
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogReviewDeleteQBinding.inflate(inflater)
        val build = AlertDialog.Builder(context).setView(binding.root)
        val dialog = build.create()
        var sign:Boolean

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnDelete.setOnClickListener{
            Log.i("DeleteQuestionDialog","삭제버튼 눌림")
            sign = it.isSelected
            it.isSelected = !sign
            listener.onPositiveClicked(true)
            dialog.dismiss()
        }
        binding.btnCancel.setOnClickListener{
            sign = it.isSelected
            it.isSelected = !sign
            dialog.dismiss()
        }
        dialog.show()
    }
}