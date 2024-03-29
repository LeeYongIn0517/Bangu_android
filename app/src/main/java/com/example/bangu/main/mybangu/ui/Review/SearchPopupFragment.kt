package com.example.bangu.main.mybangu.ui.Review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bangu.databinding.FragmentSearchpopupBinding
import com.example.bangu.main.data.model.MovieOtts
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.mybangu.ui.myInterface.Communicator

class SearchPopupFragment:Fragment() {
    private lateinit var binding:FragmentSearchpopupBinding
    private var page = 0
    private val ITEMS_SIZE = 20 //다양한 검색결과를 고려해서 최대 사이즈로 지정
    private lateinit var name_recent:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchpopupBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel = SearchPopupVM()
        val SpRvadapter = SearchPopupAdapter(object:Communicator{
            override fun passData(title: String, imageUrl: String, ott:List<MovieOtts>) {
                val bundle_title = title
                val bundle_imageUrl = imageUrl
                val bundle_ott = ott
                parentFragmentManager.setFragmentResult("requestKey", bundleOf("title" to bundle_title,"imageUrl" to bundle_imageUrl, "ott" to bundle_ott))

            }

            override fun <T> passWholeData(data: T) {
                parentFragmentManager.setFragmentResult("requestKey_whole",bundleOf("movieData" to data))
                parentFragmentManager.beginTransaction().remove(this@SearchPopupFragment).commit()//현재 프레그먼트 닫기
            }
        })

        /*영화 검색결과 리사이클뷰 초기화*/
        binding.searchResultRv.apply {
            adapter = SpRvadapter //어댑터
            layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false) //vertical
        }

        /*영화 검색하기*/
        binding.mybanguSearchIcon.setOnClickListener {
            var name = binding.mybanguSearchText.text.toString()
            name_recent = name //최근 검색어로 등록
            if(name.equals("")) { //아무것도 입력 안 하면 안됨.
                val toast = Toast.makeText(this.context, "작품명을 입력해주세요", Toast.LENGTH_LONG)
                toast.show()
            }
            else //작품명으로 영화 요청
                viewmodel.requestMovie(name, page, ITEMS_SIZE)
        }
        /*서버에서 온 영화 데이터를 관찰하는 옵저버*/
        viewmodel.movieList.observe(viewLifecycleOwner, Observer { //뷰모델에서 리스트에 서버데이터를 삽입한 걸 감지하는 역할
            binding.infotext.visibility = View.INVISIBLE
            SpRvadapter.setList(it as MutableList<MovieResponseData>)
            SpRvadapter.notifyItemRangeInserted(page*ITEMS_SIZE,ITEMS_SIZE)
        })
        /*x버튼으로 팝업 닫기*/
        binding.mybanguSearchOut.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}