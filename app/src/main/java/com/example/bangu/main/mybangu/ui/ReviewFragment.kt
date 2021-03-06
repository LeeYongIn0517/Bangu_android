package com.example.bangu.main.mybangu.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.bangu.R
import com.example.bangu.databinding.FragmentReviewBinding
import com.example.bangu.main.data.model.MovieOtts
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.mybangu.data.model.ReviewOtt

class ReviewFragment : Fragment() {
    private lateinit var binding: FragmentReviewBinding
    private lateinit var attention: String
    private lateinit var content: String
    private lateinit var dialogue: String
    private var revealed = true //초기화
    private var score:Float = 0.0f //초기화
    private lateinit var title:String
    private lateinit var movieData:MovieResponseData //리뷰 등록시 필요한 데이터클래스
    var reviewOtt=ReviewOtt(
        netflix = false,
        tving = false,
        watcha = false,
        wavve = false,
    )//리뷰 등록시 필요한 ott 인스턴스 초기화
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = ReviewViewModel()

        /*영화작품 검색팝업 띄우기*/
        binding.mybanguPlus.setOnClickListener {
            childFragmentManager.beginTransaction(). replace(R.id.search_popup_frame, SearchPopupFragment()).commit()
        }
        /*mybangu 디폴트 페이지로 돌아가기*/
        binding.backCursor.setOnClickListener {
            parentFragmentManager.beginTransaction(). replace(R.id.search_popup_frame, MyBanguFragment()).commit()
        }
        /*리뷰 등록 버튼 눌렀을 때*/
        binding.btnRegister.setOnClickListener {
            title = binding.resultMovietitle.toString() //제목
            attention = binding.mybanguAttention.toString() //감상포인트
            content = binding.mybanguContent.toString() //리뷰내용
            dialogue = binding.mybanguDialog.toString() //명대사
            if(binding.mybanguCheck.isChecked){ //비공개 여부 체크
                revealed = true
            } else revealed = false
            score = binding.reviewStarscore.rating
            //입력란 작성 여부 검사하기
            if(attention.equals("") || content.equals("") || dialogue.equals("") || title.equals("작품명")){ //작성란이 비었거나 작품선택 안 한 경우
                //다이얼로그 보여주기
                WarningDialog().show(it.context)
            }else{
//                //선택받았던 영화작품의 데이터 수신하기
//                childFragmentManager.setFragmentResultListener("requestKey_whole",viewLifecycleOwner,
//                FragmentResultListener { key, bundle ->
//                    movieData = bundle.get("movieData") as MovieResponseData
//                })
//                viewmodel.registerMyReview(attention,content,dialogue,revealed,score,movieData,reviewOtt)
            }
        }
    }
    /*SearchPuFragment에서 넘어온 값을 수신받을 시점*/
    override fun onResume() {
        super.onResume()
        Log.d("ReviewFragment","onResume called")

        childFragmentManager.setFragmentResultListener("requestKey",viewLifecycleOwner,
            FragmentResultListener{ key,bundle->
                val ottList:List<MovieOtts> = bundle.get("ott") as List<MovieOtts>

                /*수신받은 영화 이미지, 작품명, ott 바인딩*/
                binding.dashImage.visibility = View.INVISIBLE // 하얀 점선 테두리 없애기
                Glide.with(binding.root).load(bundle.getString("imageUrl")).override(Target.SIZE_ORIGINAL)
                    .into(binding.mybanguImage) //이미지
                binding.resultMovietitle.text = bundle.getString("title") //작품명

                val ottSize = ottList.size
                binding.apply{ //ott아이콘 초기화
                    reviewNetflix.visibility = View.GONE
                    reviewTving.visibility = View.GONE
                    reviewWatcha.visibility = View.GONE
                    reviewWavve.visibility = View.GONE
                }
                //ReviewOtt 인스턴스 초기화


                for(i in 0 until ottSize!!){
                    when(ottList.get(i).ottName){
                        "NETFLIX" ->{
                            binding.reviewNetflix.visibility = View.VISIBLE
                            reviewOtt.netflix = true
                        }
                        "TVING" -> {
                            binding.reviewTving.visibility = View.VISIBLE
                            reviewOtt.tving = true
                        }
                        "WATCHAPLAY" -> {
                            binding.reviewWatcha.visibility = View.VISIBLE
                            reviewOtt.watcha = true
                        }
                        "WAVVE" -> {
                            binding.reviewWavve.visibility = View.VISIBLE
                            reviewOtt.wavve = true
                        }
                    }
                }
            })
    }
}