package com.elcom.com.quizupapp.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.HomeActivity
import com.elcom.com.quizupapp.ui.activity.SoloMatchWithImageActivity
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.activity.presenter.TopicPresenter
import com.elcom.com.quizupapp.ui.view.TopicView

import java.util.ArrayList

import com.elcom.com.quizupapp.ui.adapter.*

import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.ProgressDialogUtils

import kotlinx.android.synthetic.main.fragment_topic.*



/**
 * A simple [Fragment] subclass.
 */
class ListTopicGroupFragment : BaseFragment(), TopicView {


    private var view: ViewGroup? = null
    private var mTopicPresenter:TopicPresenter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(view == null){
            view = inflater!!.inflate(R.layout.fragment_topic, null) as ViewGroup?

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProgressDialogUtils.showProgressDialog(context, 0, 0)
        mTopicPresenter = TopicPresenter(this, context!!)
        mTopicPresenter!!.getLisTopic()
    }

    private var mListCaterogy = ArrayList<Caterogy>()
    private var adapter:VerticalListTopicRecyclerAdapter? = null
    private fun initData(){

        if (recycler_view == null){
            return
        }

        recycler_view.setHasFixedSize(false)
        val layoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = layoutManager as RecyclerView.LayoutManager?
        adapter = VerticalListTopicRecyclerAdapter(mListCaterogy)
        recycler_view.adapter = adapter
        ProgressDialogUtils.dismissProgressDialog()
        adapter!!.SetOnItemClickListener(object : HorizontalListTopicRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val newFrag = NameGroupFragment()
                openFragment(newFrag)
            }

            override fun onItemLongClick(view: View, position: Int) {
                Toast.makeText(context, "long click " + position, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getListTopicSuccess(pData: List<Caterogy>) {
        if ( avi == null){
            return
        }

        mListCaterogy = pData as ArrayList<Caterogy>
        initData()
    }

    override fun getListTopicFail() {
        if ( avi == null){
            return
        }
        avi.hide()
    }

    override fun removeHistorySuccess() {

    }

    override fun removeHistoryFault() {

    }


}


