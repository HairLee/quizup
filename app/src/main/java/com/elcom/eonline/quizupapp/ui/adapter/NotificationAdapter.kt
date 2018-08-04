package com.elcom.eonline.quizupapp.ui.adapter


import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.elcom.eonline.quizupapp.R


/**
 * Created by Hailpt on 4/24/2018.
 */
class NotificationAdapter(private val moviesList: List<Int>) : RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {

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
                .inflate(R.layout.notification_item, parent, false)

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