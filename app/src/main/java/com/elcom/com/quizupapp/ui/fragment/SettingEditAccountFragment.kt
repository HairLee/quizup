package com.elcom.com.quizupapp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.adapter.SettingAdapter
import com.elcom.com.quizupapp.ui.adapter.SettingEditAccountAdapter
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_setting.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingEditAccountFragment : BaseFragment(), OnItemClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_edit_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        val myResArray = resources.getStringArray(R.array.setting_title)
        val  adapter = SettingEditAccountAdapter(myResArray);
        adapter.setListener(this)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    override fun onItemClicked(position: Int) {

    }


}
