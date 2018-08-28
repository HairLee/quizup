package com.elcom.eonline.quizupapp.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.elcom.eonline.quizupapp.R
import org.json.JSONObject
import com.elcom.eonline.quizupapp.ui.listener.OnItemClickListener
import com.elcom.eonline.quizupapp.utils.PreferUtils
import com.squareup.picasso.Picasso


/**
 * Created by Hailpt on 4/24/2018.
 */
class ChallengeFromFriendsAdapter(private var moviesList: List<String>, private var pOnItemClick: OnItemClickListener, mContext:Context) : RecyclerView.Adapter<ChallengeFromFriendsAdapter.MyViewHolder>(), Filterable {

    var valueFilter: ValueFilter? = null
    var mList = moviesList
    var mOnItemClickListener = pOnItemClick
    var context = mContext
    override fun getFilter(): Filter {
        if (valueFilter == null) {
            valueFilter = ValueFilter()
        }
        return valueFilter as ValueFilter
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tvName)
        var btnChallenge:Button = view.findViewById(R.id.btnChallenge)
        var imvAva:ImageView = view.findViewById(R.id.imvAva)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.challenge_from_friends_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val mObject = JSONObject(moviesList[position])


        if (mObject.has("name")){
            holder.tvName.text = mObject["name"].toString()
        }

        if(mObject.has("id")){
            if(mObject["id"] == PreferUtils().getUserId(context)){
                holder.btnChallenge.visibility = View.GONE
            } else {
                holder.btnChallenge.visibility = View.VISIBLE
            }

        }
        if (mObject.has("url")){
            Picasso.get().load(mObject["url"] as String).into( holder.imvAva)
        } else {
            Picasso.get().load(mObject["avatar"] as String).into( holder.imvAva)
        }

        holder.btnChallenge.setOnClickListener {
            mOnItemClickListener.onItemClicked(position)
        }

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class ValueFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val results = Filter.FilterResults()

            if (constraint != null && constraint.isNotEmpty()) {
                 val filterList = ArrayList<String>()
                for (i in 0 until mList.count()) {

                    val mObject = JSONObject(mList[i])

                    if (JSONObject(mList[i])["name"].toString().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        filterList.add(mList[i])
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = mList.count()
                results.values = mList
            }
            return results

        }

        override fun publishResults(constraint: CharSequence,
                                    results: Filter.FilterResults) {
            moviesList = results.values as List<String>
            notifyDataSetChanged()
        }

    }
}