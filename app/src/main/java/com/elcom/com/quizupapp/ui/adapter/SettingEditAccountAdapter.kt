package com.elcom.com.quizupapp.ui.adapter


import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener


/**
 * Created by Hailpt on 4/24/2018.
 */
class SettingEditAccountAdapter(private val moviesList: Array<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var profile:Profile? = null
    private val PROFILE_VIEW = 1
    private val MY_PROFILE = 9
    private var bitmap:Bitmap? = null
    private var mItemClickListener: OnItemClickListener? = null


    fun setData(profile:Profile){
        this.profile = profile
        notifyDataSetChanged()
    }

    inner class FACEBOOK_VIEW(view: View) : RecyclerView.ViewHolder(view) {
        var rlClick: RelativeLayout = view.findViewById(R.id.rlClick)
    }

    inner class MYPROFILE(view: View) : RecyclerView.ViewHolder(view) {

        var title: TextView = view.findViewById(R.id.tvTitle)
        var tvNumberOfTopic: TextView = view.findViewById(R.id.tvNumberOfTopic)
        var imvAva: ImageView = view.findViewById(R.id.imvAva)
        var viewBottom: View = view.findViewById(R.id.viewBottom)
        var rlClick: RelativeLayout = view.findViewById(R.id.rlClick)
        var lnNumberOfTopic: LinearLayout = view.findViewById(R.id.lnNumberOfTopic)
        var imvDes: ImageView = view.findViewById(R.id.imvDes)
    }

    fun setListener(mListener:OnItemClickListener){
        mItemClickListener = mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){

            PROFILE_VIEW ->  FACEBOOK_VIEW(LayoutInflater.from(parent.context)
                    .inflate(R.layout.setting_facebook_item, parent, false))

            MY_PROFILE ->  MYPROFILE(LayoutInflater.from(parent.context)
                    .inflate(R.layout.setting_account_item_style_1_layout, parent, false))

            else ->  MYPROFILE(LayoutInflater.from(parent.context)
                    .inflate(R.layout.setting_account_item_style_1_layout, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (holder is MYPROFILE){
            holder.title.text = moviesList[position]

            when(position){

//                0->{
//                    holder.imvDes.visibility = View.VISIBLE
//                    holder.imvDes.setImageResource(R.drawable.update_name_edit_ic)
//                    holder.imvAva.setImageResource(R.drawable.update_name_ic)
//                }

                0->{
                    holder.imvDes.visibility = View.VISIBLE
                    holder.imvDes.setImageResource(R.drawable.update_warning_ic)
                    holder.imvAva.setImageResource(R.drawable.update_device_id_ic)
                    if (profile != null){
                        holder.title.text = profile!!.email
                    }
                }

                1 -> {
                    holder.imvDes.visibility = View.VISIBLE
                    holder.imvDes.setImageResource(R.drawable.setting_view_more_ic)
                    holder.viewBottom.visibility = View.INVISIBLE
                    holder.imvAva.setImageResource(R.drawable.update_pw)

                }

                2 -> {
                    holder.imvAva.setImageResource(R.drawable.update_title)
                    holder.viewBottom.visibility = View.INVISIBLE
                }

                3 -> {
                    holder.tvNumberOfTopic.visibility = View.VISIBLE
                    holder.tvNumberOfTopic.text = "Cập nhật"
                    holder.viewBottom.visibility = View.INVISIBLE
//                    holder.imvAva.setImageResource(R.drawable.ic_ava_default)
                    if(bitmap != null){
                        holder.imvAva!!.setImageBitmap(bitmap)
                    }
                }

                4 -> {
                    holder.tvNumberOfTopic.visibility = View.VISIBLE
                    holder.tvNumberOfTopic.text = "Cập nhật"
                    holder.viewBottom.visibility = View.INVISIBLE
                    holder.imvAva.setImageResource(R.drawable.update_cover_ic)
                }

                5 -> {
                    holder.tvNumberOfTopic.text = "Sửa"
                    holder.tvNumberOfTopic.visibility = View.VISIBLE
                    holder.viewBottom.visibility = View.INVISIBLE
                    holder.imvAva.setImageResource(R.drawable.update_gender)
                }

                6 -> {
                    holder.viewBottom.visibility = View.INVISIBLE
                    holder.imvAva.setImageResource(R.drawable.setting_vote_ic)
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

    fun setAvaBitmap(bitmap:Bitmap){
        this.bitmap = bitmap
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){

            4 -> MY_PROFILE

            else -> MY_PROFILE
        }
    }
}