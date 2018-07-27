package com.elcom.com.quizupapp.ui.adapter


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.R.id.imvAva
import com.elcom.com.quizupapp.ui.activity.model.entity.Followed.FollowedList
import com.squareup.picasso.Picasso


/**
 * Created by Hailpt on 4/24/2018.
 */
class SettingFriendFollowedAdapter(private val activity: Activity, private val moviesList: List<FollowedList>) : RecyclerView.Adapter<SettingFriendFollowedAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tvName)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.setting_friend_followed_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.tvName.text = movie.name
        Picasso.get()
                .load(movie.avatar)
                .into(holder.imvAva)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }


}