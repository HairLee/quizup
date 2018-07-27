package com.elcom.com.quizupapp.ui.adapter


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallenge
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.utils.LogUtils


/**
 * Created by Hailpt on 4/24/2018.
 */
class LiveChallengePrepareToPlayAdapter(private val activity: Activity, private val moviesList:  ArrayList<LiveChallenge>, private val pOnItemClickListener: OnItemClickListener) : RecyclerView.Adapter<LiveChallengePrepareToPlayAdapter.MyViewHolder>() {



    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var btnDetail: Button = view.findViewById(R.id.btnDetail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.live_challenge_prepare_to_play_item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]

        if(movie.registerOrNot == -1){
            holder.btnDetail.visibility = View.INVISIBLE
        } else {
            holder.btnDetail.visibility = View.VISIBLE
        }

        holder.btnDetail.setOnClickListener {
            pOnItemClickListener.onItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }


}