package com.elcom.com.quizupapp.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.HistoryListActivity
import com.elcom.com.quizupapp.ui.activity.SoloQuestionIntro
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.activity.model.entity.SoloMatch
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.activity.presenter.TopicPresenter
import com.elcom.com.quizupapp.ui.adapter.HomeAdapter
import com.elcom.com.quizupapp.ui.adapter.HomePlayingRecyclerAdapter
import com.elcom.com.quizupapp.ui.listener.OnHistoryListListener
import com.elcom.com.quizupapp.ui.listener.OnSeeMoreTopicsListener
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.TopicView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.PreferUtils
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_header_item_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.DialogInterface
import com.elcom.com.quizupapp.ui.activity.NotificationActivity
import com.elcom.com.quizupapp.ui.activity.SettingProfileActivity
import com.facebook.FacebookSdk.getApplicationContext
import com.squareup.picasso.Picasso


/**
 * Created by Hailpt on 4/26/2018.
 */
class HomeFragment :  Fragment(), OnSeeMoreTopicsListener, OnHistoryListListener, TopicView, HomePlayingRecyclerAdapter.OnItemClickListener {



    private var view: ViewGroup? = null
    private var mTopicPresenter: TopicPresenter? = null
    private var isNeedToRefreshFragment = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        if(view == null && isNeedToRefreshFragment){
        isNeedToRefreshFragment = false
        view = inflater!!.inflate(R.layout.fragment_home, null, false) as ViewGroup?
        ProgressDialogUtils.showProgressDialog(context, 0, 0)
        mTopicPresenter = TopicPresenter(this, context!!)
        mTopicPresenter!!.getLisTopic()
//        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){

        rlNoti.setOnClickListener {
            var intent = Intent(context, NotificationActivity::class.java)
            startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)

        }

        if(ConstantsApp.USER_AVATAR_ME != ""){
            Picasso.get()
                    .load(ConstantsApp.USER_AVATAR_ME)
                    .into(imvAva)
        }


        edtSearch.setOnFocusChangeListener { view, b ->
            if (b){
                imvSearch.visibility = View.GONE
                txtSearch.visibility = View.GONE
            } else {
                imvSearch.visibility = View.VISIBLE
                txtSearch.visibility = View.VISIBLE
            }
        }

        imvAva.setOnClickListener {
            startActivity(Intent(context, SettingProfileActivity::class.java))
        }
    }


    private var mData: List<Caterogy>? = null
    override fun getListTopicSuccess(pData: List<Caterogy>) {

        mData = pData
        ProgressDialogUtils.dismissProgressDialog()
        val mAdapter = HomeAdapter(mData, this,this)
        mAdapter.SetOnItemClickListener(this)
        mAdapter.SetOnSeeMoreTopicListener(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = mAdapter
    }

    override fun getListTopicFail() {
        ProgressDialogUtils.dismissProgressDialog()
    }

    override fun onItemClick(view: View?, pTopic: Topic?) {
        var intent = Intent(activity, SoloQuestionIntro::class.java)
        intent.putExtra(ConstantsApp.KEY_QUESTION_ID,pTopic!!.topic_id)
        intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,pTopic.match_id!!)
        intent.putExtra(ConstantsApp.KEY_TYPE_OF_GAME,2)
        startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
    }

    override fun onItemLongClick(view: View?, position: Int) {

    }

    override fun onRemoveHistory(topic: Topic) {


        val builder = AlertDialog.Builder(context)

        builder.setMessage("Bạn có chắc muốn xóa?")
        builder.setPositiveButton("Có", DialogInterface.OnClickListener { dialog, which ->
            mTopicPresenter!!.removeHistory(topic.match_id!!.toInt())
            dialog.dismiss()
        })

        builder.setNegativeButton("Không", DialogInterface.OnClickListener { dialog, which ->
            // Do nothing
            dialog.dismiss()
        })

        val alert = builder.create()
        alert.show()

    }

    override fun removeHistorySuccess() {
        mTopicPresenter!!.getLisTopic()
    }

    override fun removeHistoryFault() {
    }

    override fun onPlayTopicAgain(topic: Topic) {
        RestClient().getInstance().getRestService().getTopicMatchId(PreferUtils().getUserId(context!!), topic.topic_id.toString()).enqueue(object : Callback<RestData<SoloMatch>> {
            override fun onResponse(call: Call<RestData<SoloMatch>>?, response: Response<RestData<SoloMatch>>?) {
                ProgressDialogUtils.dismissProgressDialog()
                if (response?.body() != null){
                    var intent = Intent(context, SoloQuestionIntro::class.java)
                    intent.putExtra(ConstantsApp.KEY_QUESTION_ID,response.body().data!!.topicId)
                    intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,response.body().data!!.id)
                    startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
                }
            }

            override fun onFailure(call: Call<RestData<SoloMatch>>?, t: Throwable?) {

            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        isNeedToRefreshFragment = true
        mTopicPresenter = TopicPresenter(this, context!!)
        mTopicPresenter!!.getLisTopic()
    }


    override fun onSeeMoreTopics(type:Int) {
        var intent = Intent(context, HistoryListActivity::class.java)
        startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_HISTORY)
    }


}