package com.elcom.com.quizupapp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Cheeses
import com.elcom.com.quizupapp.ui.activity.model.entity.response.Statistical
import com.elcom.com.quizupapp.ui.adapter.SimpleStringRecyclerViewAdapter
import com.elcom.com.quizupapp.ui.adapter.StatisticAdapter
import kotlinx.android.synthetic.main.fragment_statistic_world.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class StatisticWorldFragment : Fragment() {


    private var mList = listOf<Statistical>()
    private var adapter:StatisticAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rv = inflater.inflate(
                R.layout.fragment_statistic_world, container, false) as RecyclerView
        return rv
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = StatisticAdapter(mList)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
    }

    fun setData(pList:List<Statistical>){
        mList = pList
        adapter!!.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {

    }

}
