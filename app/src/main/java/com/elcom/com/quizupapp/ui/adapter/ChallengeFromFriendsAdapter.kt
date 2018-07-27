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
import org.json.JSONObject
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener


/**
 * Created by Hailpt on 4/24/2018.
 */
class ChallengeFromFriendsAdapter(private var moviesList: List<String>, private var pOnItemClick: OnItemClickListener) : RecyclerView.Adapter<ChallengeFromFriendsAdapter.MyViewHolder>(), Filterable {

    var valueFilter: ValueFilter? = null
    var mList = moviesList
    var mOnItemClickListener = pOnItemClick

    override fun getFilter(): Filter {
        if (valueFilter == null) {
            valueFilter = ValueFilter()
        }
        return valueFilter as ValueFilter
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tvName)
        var btnChallenge:Button = view.findViewById(R.id.btnChallenge)
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