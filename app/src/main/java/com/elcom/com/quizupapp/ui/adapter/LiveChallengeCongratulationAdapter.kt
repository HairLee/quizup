package com.elcom.com.quizupapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallengeResult
import org.json.JSONObject
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener


/**
 * Created by Hailpt on 4/24/2018.
 */
class LiveChallengeCongratulationAdapter(private var pLiveChallengeResults: List<LiveChallengeResult>, private var pOnItemClick: OnItemClickListener) : RecyclerView.Adapter<LiveChallengeCongratulationAdapter.MyViewHolder>() {


    var mLiveChallengeResults = pLiveChallengeResults
    var mOnItemClickListener = pOnItemClick



    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var tvName: TextView = view.findViewById(R.id.tvName)
//        var btnChallenge:Button = view.findViewById(R.id.btnChallenge)
//        var tvScore:Button = view.findViewById(R.id.tvScore)
//        var tvCoin:Button = view.findViewById(R.id.tvCoin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.live_challenge_congratulations_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return mLiveChallengeResults.size
    }


}