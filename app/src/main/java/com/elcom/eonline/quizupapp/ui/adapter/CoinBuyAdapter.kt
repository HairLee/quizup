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
import com.elcom.eonline.quizupapp.ui.listener.OnItemClickListener
import com.squareup.picasso.Picasso


/**
 * Created by Hailpt on 4/24/2018.
 */
class CoinBuyAdapter(private val topicDetails: List<BuyCoin>) : RecyclerView.Adapter<CoinBuyAdapter.MyViewHolder>() {



    var mOnItemClickListener: OnItemClickListener? = null
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
        holder.btnBuy.text = topic.getVnd()

        holder.btnBuy.setOnClickListener {
            mOnItemClickListener!!.onItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return topicDetails.size
    }

    fun setOnItemClickListenerr(pOnItemClickListener: OnItemClickListener){
        mOnItemClickListener = pOnItemClickListener
    }
}