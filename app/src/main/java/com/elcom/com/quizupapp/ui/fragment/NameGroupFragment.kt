package com.elcom.com.quizupapp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elcom.com.quizupapp.R
import android.support.v7.widget.DefaultItemAnimator
import com.facebook.FacebookSdk.getApplicationContext
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.elcom.com.quizupapp.ui.adapter.GroupNameAdapter
import kotlinx.android.synthetic.main.fragment_name_group.*


class NameGroupFragment : Fragment() {

    val numbers: MutableList<Int> = mutableListOf(1, 2, 34,5,6,7,8,9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){

        val mAdapter =  GroupNameAdapter(numbers)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter

    }


}
