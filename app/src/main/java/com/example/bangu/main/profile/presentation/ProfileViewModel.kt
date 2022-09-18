package com.example.bangu.main.profile.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.App
import com.example.bangu.main.profile.data.ProfileDataResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProfileViewModel:ViewModel() {
    private val profileService = ProfileDataResource.ProfileApi
    private var _username = MutableLiveData<String>()
    val username: LiveData<String> = _username
    private var _followerNum = MutableLiveData<String>()
    val followerNum: LiveData<String> = _followerNum
    private var _followingNum = MutableLiveData<String>()
    val followingNum: LiveData<String> = _followingNum
    private var _bookmarkNum = MutableLiveData<String>()
    val bookmarkNum: LiveData<String> = _bookmarkNum

    /**사용자 토큰 및 식별자*/
    val accessToken = App.token_prefs.accessToken
    val id = App.signup_prefs.sp_id

    /**사용자 조회 요청 함수*/
    fun requestUser(disposable: CompositeDisposable){
        if(accessToken != null){
            disposable.add(
                profileService.requestUser(accessToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("ProfileViewModel","requestUser().success")
                        _username.postValue(it.nickname)
                    }){
                        Log.d("FeedViewModel","requestUser().fail")
                    }
            )
        }
    }
    /**유저의 팔로잉 리스트를 가져옵니다*/
    fun requestFollowing(page:Int,size:Int,disposables: CompositeDisposable){
        if (accessToken != null) {
            disposables.add(
                profileService.requestFollowing(accessToken,id,page,size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("ProfileViewModel","requestFollowing().success")
                        _followingNum.postValue(it.followings.toString())
                    }){
                        Log.d("ProfileViewModel","requestFollowing().fail")
                    }
            )
        }
    }
    /**유저의 팔로워 리스트를 가져옵니다*/
    fun requestFollower(page:Int,size:Int,disposables: CompositeDisposable){
        if (accessToken != null) {
            disposables.add(
                profileService.requestFollower(accessToken, id, page, size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("ProfileViewModel", "requestFollower().success")
                        _followerNum.postValue(it.followers.toString())
                    }) {
                        Log.d("ProfileViewModel", "requestFollower().fail")
                    }
            )
        }
    }
    /**유저가 북마크한 리뷰를 가져옵니다*/
    fun requestBookmark(page:Int, size:Int, disposable: CompositeDisposable){
        if (accessToken != null) {
            disposable.add(
                profileService.requestBookmark(accessToken, page, size)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("ProfileViewModel","requestBookmark().success")
                        _bookmarkNum.postValue(it.totalElements.toString())
                    }) {
                        Log.d("ProfileViewModel","requestBookmark().fail")
                    }
            )
        }
    }
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