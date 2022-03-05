package com.example.bangu.main.ui

import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.R
import com.example.bangu.signup.ui.SignupFinAdapter
import org.w3c.dom.Text

class HomeAdapter():RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var list_title=listOf<String>("짱구극장판1","짱구극장판2","짱구극장판3")
    var list_score = listOf<Int>(1,2,3)
    var list_content= listOf<String>("짱","졸잼","지렸다")

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var review_image:ImageView
        var review_title:TextView
        var review_score:RatingBar
        var review_content:TextView
//        var review_bookmark:AppCompatButton
        init {
//            var sign:Boolean
            review_image=view.findViewById(R.id.review_movieimage)
            review_title=view.findViewById(R.id.review_movietitle)
            review_score=view.findViewById(R.id.review_starscore)
            review_content=view.findViewById(R.id.review_core)
//            review_bookmark=view.findViewById(R.id.review_bookmark)
//            review_bookmark.setOnClickListener {
//                sign = review_bookmark.isSelected
//                review_bookmark.isSelected = !sign
//            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_item, parent, false)

        return HomeAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.review_title.text = list_title.get(position)
        holder.review_score.rating = (list_score.get(position)).toFloat()
        holder.review_content.text = list_content.get(position)
    }
    override fun getItemCount(): Int {
        return 3
    }
}