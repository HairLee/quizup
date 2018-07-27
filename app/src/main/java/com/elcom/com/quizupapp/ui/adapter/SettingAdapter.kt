package com.elcom.com.quizupapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener


/**
 * Created by Hailpt on 4/24/2018.
 */
class SettingAdapter(private val moviesList: Array<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    private val PROFILE_VIEW = 1
    private val MY_PROFILE = 9

    private var mItemClickListener: OnItemClickListener? = null

    inner class FACEBOOK_VIEW(view: View) : RecyclerView.ViewHolder(view) {
        var rlClick: RelativeLayout = view.findViewById(R.id.rlClick)
    }

    inner class MYPROFILE(view: View) : RecyclerView.ViewHolder(view) {

        var title: TextView = view.findViewById(R.id.tvTitle)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)
        var viewBottom: View = view.findViewById(R.id.viewBottom)
        var rlClick: RelativeLayout = view.findViewById(R.id.rlClick)
        var lnNumberOfTopic: LinearLayout = view.findViewById(R.id.lnNumberOfTopic)
    }

    fun setListener(mListener:OnItemClickListener){
        mItemClickListener = mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){

            PROFILE_VIEW ->  FACEBOOK_VIEW(LayoutInflater.from(parent.context)
                    .inflate(R.layout.setting_facebook_item, parent, false))

            MY_PROFILE ->  MYPROFILE(LayoutInflater.from(parent.context)
                    .inflate(R.layout.setting_item_style_1_layout, parent, false))

             else ->  MYPROFILE(LayoutInflater.from(parent.context)
                     .inflate(R.layout.setting_item_style_1_layout, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (holder is MYPROFILE){
            holder.title.text = moviesList[position]

            when(position){

                0->{
                    holder.imvAva.setImageResource(R.drawable.setting_my_profile_ic)
                }

//                1->{
//                    holder.imvAva.setImageResource(R.drawable.setting_level_ic)
//
//                }

                1 -> {
                    holder.imvAva.setImageResource(R.drawable.setting_friend_ic)

                }

                2 -> {
                    holder.imvAva.setImageResource(R.drawable.setting_favor_topic_ic)
//                    holder.lnNumberOfTopic.visibility = View.VISIBLE
//                    holder.viewBottom.visibility = View.INVISIBLE
                }

//                3 -> {
//                    holder.imvAva.setImageResource(R.drawable.setting_facebook_ic)
//                }

                3 -> {
                    holder.imvAva.setImageResource(R.drawable.setting_about_me_ic)
                }

                4 -> {
                    holder.imvAva.setImageResource(R.drawable.setting_support_ic)
                }

                5 -> {
                    holder.imvAva.setImageResource(R.drawable.setting_vote_ic)
                    holder.viewBottom.visibility = View.INVISIBLE
                }

            }
            holder.rlClick.setOnClickListener {
                mItemClickListener!!.onItemClicked(position)
            }
        } else  if (holder is FACEBOOK_VIEW){
            holder.rlClick.setOnClickListener {
                mItemClickListener!!.onItemClicked(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){

            3 -> MY_PROFILE

            else -> MY_PROFILE
        }
    }
}