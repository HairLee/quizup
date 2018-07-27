package com.elcom.com.quizupapp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Followed.Followed
import com.elcom.com.quizupapp.ui.activity.presenter.SettingFollowedPresenter
import com.elcom.com.quizupapp.ui.adapter.SettingFriendFollowedAdapter
import com.elcom.com.quizupapp.ui.view.SettingFollowedView
import com.elcom.com.quizupapp.utils.PreferUtils
import kotlinx.android.synthetic.main.fragment_setting_friend.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingFriendFragment : Fragment(), SettingFollowedView {


    private val mSettingFollowedPresenter = SettingFollowedPresenter(this)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_friend, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSettingFollowedPresenter.getFriends(PreferUtils().getUserId(context!!))
    }

    override fun getDataSuccessfully(profile: Followed) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = SettingFriendFollowedAdapter(activity!!,
                profile.list!!)
    }

    override fun getDataFault() {

    }
}
