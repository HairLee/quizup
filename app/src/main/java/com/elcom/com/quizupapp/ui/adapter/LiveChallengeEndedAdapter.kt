package com.elcom.com.quizupapp.ui.adapter


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import com.elcom.com.quizupapp.R


/**
 * Created by Hailpt on 4/24/2018.
 */
class LiveChallengeEndedAdapter(private val activity: Activity, private val moviesList: List<String>) : RecyclerView.Adapter<LiveChallengeEndedAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var title: TextView
//        var year: TextView
//        var genre: TextView

        init {
//            title = view.findViewById(R.id.title)
//            genre = view.findViewById(R.id.genre)
//            year = view.findViewById(R.id.year)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.live_challenge_ended_item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
//        holder.title.setText(movie.getTitle())
//        holder.genre.setText(movie.getGenre())
//        holder.year.setText(movie.getYear())
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }


}