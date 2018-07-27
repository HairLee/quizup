package com.elcom.com.quizupapp.ui.adapter


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.elcom.com.quizupapp.R
import android.text.Html
import android.widget.TextView


/**
 * Created by Hailpt on 4/24/2018.
 */
class LiveChallengeCommentAdapter(private val activity: Activity, private val moviesList: List<String>) : RecyclerView.Adapter<LiveChallengeCommentAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var imvShape: ImageView
        var tvChatName: TextView
//        var genre: TextView

        init {
//            imvShape = view.findViewById(R.id.imvShare)
            tvChatName = view.findViewById(R.id.tvChatName)
//            year = view.findViewById(R.id.year)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.live_challenge_comment_item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]

        changeNameColor(holder.tvChatName)
//        holder.title.setText(movie.getTitle())
//        holder.genre.setText(movie.getGenre())
//        holder.year.setText(movie.getYear())
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    private fun changeNameColor(tv:TextView){
        val first = " Game trí tuệ, hay, hấp dẫn,Phù "
        val next = "<font color='#82F6FF'>Ander Levine</font>"
        tv.text = Html.fromHtml(next +first )
    }


}