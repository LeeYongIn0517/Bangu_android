package com.example.bangu.signup.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import android.widget.ImageButton
import com.example.bangu.R

class Signup_Dialog_warning(context: Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: DialogInterface.OnClickListener
    fun setOnClickListener(listener:DialogInterface.OnClickListener){
        onClickListener = listener
    }
    fun showDialog(){
        val btn = dialog.findViewById<ImageButton>(R.id.dialog_button)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_signup_warn)
        }
        btn.setOnClickListener{
            dialog.dismiss()
        }
    }
    fun designDialog(){
        dialog.show();

    }
}