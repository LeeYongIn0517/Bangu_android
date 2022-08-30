package com.example.bangu.main.profile.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.bangu.SingleActivity
import com.example.bangu.main.profile.ui.ProfileFragment

class ProfileViewModel:ViewModel() {
    /**파일 접근 권한 확인 함수*/
//    fun checkSelfPermission(){
//        val RESULT_CODE = 1
//
//        //lateinit var permissions:MutableList<String>
//        val permissions = arrayOf(
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        )
//        val contract = ActivityResultContracts.RequestMultiplePermissions()
//        val activityResultLauncher = registerForActivityResult(contract){ resultMap ->
//            val isAllGranted = permissions.all{ e -> resultMap[e] == true }
//
//            if(isAllGranted){
//                //모든 권한이 필요한 작업 수행
//            }
//        }
//
//        /**파일 읽기 권한 확인*/
//        if(ProfileFragment().context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) } != PackageManager.PERMISSION_GRANTED){
//            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
//        }
//
//        /**파일 쓰기 권한 확인*/
//        if(ProfileFragment().context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.WRITE_EXTERNAL_STORAGE) } != PackageManager.PERMISSION_GRANTED){
//            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        }
//
//        if(TextUtils.isEmpty(temp) == false){
//            /**권한 요청*/
//            requestPers(permissions.toTypedArray(),RESULT_CODE)
//        }else{
//            /**모두 허용 상태*/
//            Toast.makeText(ProfileFragment().context,"권한을 모두 허용", Toast.LENGTH_SHORT).show()
//        }
//    }
}