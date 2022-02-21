package com.example.bangu.signup.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.R

class SignupFinAdapter():RecyclerView.Adapter<SignupFinAdapter.ViewHolder>() {
    var list = listOf<Int>(1,2,3)
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val movieimage :ImageView
        val moviestar :RatingBar
        init {
            movieimage = view.findViewById(R.id.movieimage)
            moviestar = view.findViewById(R.id.moviestar)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.signup_fin_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SignupFinAdapter.ViewHolder, position: Int) {
        //holder에 데이터바인딩
//        holder.movieimage.setImageURI(getMovieUri()) //서버에서 온 데이터
//        holder.moviestar.rating = getMovieStar() //서버에서 온 데이터
        holder.moviestar.rating = (list.get(position)).toFloat()
    }

    //전체 아이템 개수 리턴
    override fun getItemCount(): Int {
        return 3
    }
}