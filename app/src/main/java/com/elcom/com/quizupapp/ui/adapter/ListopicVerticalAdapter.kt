package com.elcom.com.quizupapp.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.listener.OnSeeMoreCaterogyListener
import com.elcom.com.quizupapp.ui.listener.OnSeeMoreTopicsListener
import org.json.JSONObject

/**
 * Created by Hailpt on 5/18/2018.
 */
class ListopicVerticalAdapter(private var mList: List<Caterogy>?, private val onSeeMoreTopicsListener: OnSeeMoreCaterogyListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private val listPosition = SparseIntArray()
    private var mItemClickListener: HorizontalRecyclerAdapter.OnItemClickListener? = null
    private var mContext: Context? = null
    var pList = mList
    var valueFilter: ValueFilter? = null
    override fun getFilter(): Filter? {
        if (valueFilter == null) {
            valueFilter = ValueFilter()
        }
        return valueFilter as ValueFilter
    }

    private inner class CellViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mRecyclerView: RecyclerView
        val mTitle: TextView
        val tvMore: TextView

        init {

            mRecyclerView = itemView.findViewById<View>(R.id.recyclerView) as RecyclerView
            mTitle = itemView.findViewById<View>(R.id.txt_topic_title) as TextView
            tvMore = itemView.findViewById<View>(R.id.tvMore) as TextView
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = viewGroup.context

        when (viewType) {

            FAVOURITE_TOPIC -> {
                val v2 = LayoutInflater.from(viewGroup.context).inflate(R.layout.detail_list_item_vertical, viewGroup, false)
                return CellViewHolder(v2)
            }

            else -> {

            }
        }

        return CellViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.detail_list_item_vertical, viewGroup, false));
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {


        when (getItemViewType(position)) {

            FAVOURITE_TOPIC -> {
                val cellViewHolder = viewHolder as CellViewHolder
                cellViewHolder.mTitle.text = mList!![position].name
                cellViewHolder.mRecyclerView.setHasFixedSize(true)
                val layoutManager = LinearLayoutManager(mContext)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                cellViewHolder.mRecyclerView.layoutManager = layoutManager
                val adapter = HorizontalRecyclerAdapter(mList!![position].topics)
                cellViewHolder.mRecyclerView.adapter = adapter
                adapter.SetOnItemClickListener(mItemClickListener)
                cellViewHolder.tvMore.setOnClickListener { onSeeMoreTopicsListener.onSeeMoreTopics(1,mList!![position]) }

                val lastSeenFirstPosition = listPosition.get(position, 0)
                //                if (lastSeenFirstPosition >= 0) {
                //                    cellViewHolder.mRecyclerView.scrollToPosition(lastSeenFirstPosition);
                //                }
                setAnimation(cellViewHolder.itemView, position, lastSeenFirstPosition)
            }
        }
    }

    override fun onViewRecycled(viewHolder: RecyclerView.ViewHolder) {
        val position = viewHolder!!.adapterPosition
        if (position == 1 || position == 2) {
            val cellViewHolder = viewHolder as CellViewHolder?
            val layoutManager = cellViewHolder!!.mRecyclerView.layoutManager as LinearLayoutManager
            val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
            listPosition.put(position, firstVisiblePosition)
        }
        super.onViewRecycled(viewHolder)
    }


    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    fun SetOnItemClickListener(mItemClickListener: HorizontalRecyclerAdapter.OnItemClickListener) {
        this.mItemClickListener = mItemClickListener
    }

    override fun getItemViewType(position: Int): Int {

        when (position) {
            0 -> return FAVOURITE_TOPIC
            else -> return FAVOURITE_TOPIC
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    private fun setAnimation(viewToAnimate: View, position: Int, lastPosition: Int) {
        var lastPosition = lastPosition
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    companion object {
        private val FAVOURITE_TOPIC = 1
    }

    inner class ValueFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val results = Filter.FilterResults()

            if (constraint != null && constraint.isNotEmpty()) {
                val filterList = ArrayList<Caterogy>()
                for (i in 0 until pList!!.count()) {
                    if (pList!![i].name.toString().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        filterList.add(pList!![i])
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = pList!!.count()
                results.values = pList
            }
            return results

        }

        override fun publishResults(constraint: CharSequence,
                                    results: Filter.FilterResults) {
            if (results.values != null ){
                mList = results.values as List<Caterogy>
                notifyDataSetChanged()
            }
        }

    }
}