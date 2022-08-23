package com.example.bangu.main.profile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bangu.Event
import com.example.bangu.R
import com.example.bangu.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private var _toSetting = MutableLiveData<Event<Boolean>>()
    val toSetting: LiveData<Event<Boolean>> = _toSetting
    private var _toFollower = MutableLiveData<Event<Boolean>>()
    val toFollower: LiveData<Event<Boolean>> = _toFollower
    private var _toFollowing = MutableLiveData<Event<Boolean>>()
    val toFollowing: LiveData<Event<Boolean>> = _toFollowing
    private var _toBookmark = MutableLiveData<Event<Boolean>>()
    val toBookmark: LiveData<Event<Boolean>> = _toBookmark

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        val view = binding.root
        /**개별페이지로 이동*/
        binding.apply {
            /**설정 페이지*/
            icSetting.setOnClickListener{
                _toSetting.postValue(Event(true))
            }
            /**팔로워 페이지*/
            spaceFollower.setOnClickListener{
                _toFollower.postValue(Event(true))
            }
            /**팔로잉 페이지*/
            spaceFollowing.setOnClickListener{
                _toFollowing.postValue(Event(true))
            }
            /**북마크 페이지*/
            spaceBookmark.setOnClickListener{
                _toBookmark.postValue(Event(true))
            }
        }
        return view
    }
}