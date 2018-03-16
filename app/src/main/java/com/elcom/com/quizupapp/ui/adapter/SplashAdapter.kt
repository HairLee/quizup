package com.elcom.com.quizupapp.ui.adapter

import android.content.ClipData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Person
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.utils.LogUtils
import java.util.*

/**
 * Created by admin on 3/8/2018.
 */
class SplashAdapter(val mContext : Context, val userList: ArrayList<Person>, pOnItemClickListener: OnItemClickListener ): RecyclerView.Adapter<SplashAdapter.ViewHolder>() {

    val mOnItemClickListener = pOnItemClickListener

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.txtName?.text = userList[position].getCompanyName()
        holder?.txtTitle?.text = userList[position].getBackCamera()

        holder?.imvIcon?.setOnClickListener(View.OnClickListener {
            mOnItemClickListener.onItemClicked(position)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_splash_layout, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        @BindView(R.id.txtName) lateinit var txtName: TextView
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        val imvIcon = itemView.findViewById<ImageView>(R.id.imvIcon)

    }

}