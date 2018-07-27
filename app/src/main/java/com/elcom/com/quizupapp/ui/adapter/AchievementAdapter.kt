package com.elcom.com.quizupapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.response.ListDetail
import com.squareup.picasso.Picasso


/**
 * Created by Hailpt on 4/24/2018.
 */
class AchievementAdapter(private val topicDetails: List<ListDetail>) : RecyclerView.Adapter<AchievementAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.txt_topic_title)
        var tvCoins: TextView = view.findViewById(R.id.tvCoins)
        var tvLevel: TextView = view.findViewById(R.id.tvLevel)
        var tvDes: TextView = view.findViewById(R.id.tvDes)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.achivement_item_type_title, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val topic = topicDetails[position]
        holder.name.text = topic.name
        holder.tvCoins.text = topic.coin.toString()
        holder.tvLevel.text = topic.level.toString()
        holder.tvDes.text = topic.description

        Picasso.get()
                .load(topic.imageUnlock)
                .into(holder.imvAva)

    }

    override fun getItemCount(): Int {
        return topicDetails.size
    }
}