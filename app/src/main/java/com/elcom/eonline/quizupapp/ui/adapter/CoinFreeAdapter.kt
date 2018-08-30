package com.elcom.eonline.quizupapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.model.entity.coin.BuyCoin
import com.elcom.eonline.quizupapp.ui.activity.model.entity.coin.FreeCoins
import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.ListDetail
import com.squareup.picasso.Picasso


/**
 * Created by Hailpt on 4/24/2018.
 */
class CoinFreeAdapter(private val topicDetails: List<BuyCoin>) : RecyclerView.Adapter<CoinFreeAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName)
        var btnBuy: TextView = view.findViewById(R.id.btnBuy)
        var tvDes: TextView = view.findViewById(R.id.tvDes)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.coin_payment_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val topic = topicDetails[position]
        holder.name.text = topic.getName()
        holder.tvDes.text = topic.getDescription()
        holder.btnBuy.text = topic.getCoins().toString()
    }

    override fun getItemCount(): Int {
        return topicDetails.size
    }
}