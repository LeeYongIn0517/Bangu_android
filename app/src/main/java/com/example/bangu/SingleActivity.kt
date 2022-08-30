package com.example.bangu

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.bangu.databinding.ActivitySingleBinding
import com.example.bangu.login.ui.LoginFragment
import com.example.bangu.main.ui.MainFragment

class SingleActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySingleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(App.token_prefs.accessToken.equals("")){ //토큰이 존재하면 메인페이지를 추가하며 시작
            Log.i("SingleActivity","toMainFragment()")
            supportFragmentManager.beginTransaction().add(R.id.singleFrame,MainFragment()).commit()
        }else{
            Log.i("SingleActivity","toLoginFragment()")
            supportFragmentManager.beginTransaction().add(R.id.singleFrame,LoginFragment()).commit() //로그인 화면으로 가서 회원가입 유도
        }
    }

    /**ProfileFragment에서 사진에 대한 권한요청 처리결과를 수신*/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
           0 -> {
               if(grantResults.isNotEmpty()){
                   var isAllGranted = true
                   /**요청한 권한 허용/거부 상태를 한번에 체크*/
                   for(grant in grantResults){
                       if(grant != PackageManager.PERMISSION_GRANTED){
                           isAllGranted = false
                           break
                       }
                   }

                   if(isAllGranted){ /**요청한 권한을 모두 허용한 경우*/
                       //다음 단계
                   }else{ /**허용되지 않은 권한이 있는 경우 -> 필수권한/선택권한 여부에 따라서 별도 처리를 해주어야 함*/
                       if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE) ||
                               !ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                           //다시 묻지 않기 체크하면서 권한 거부 되었음(?)
                       }else{
                           //접근 권한을 거부하였음(?)
                       }
                   }
               }
           }
        }
    }
}