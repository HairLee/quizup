package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.adapter.FavouriteTopicAdapter
import com.elcom.com.quizupapp.ui.adapter.HorizontalRecyclerAdapter
import com.elcom.com.quizupapp.ui.adapter.SettingAdapter
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ConstantsApp
import kotlinx.android.synthetic.main.activity_favourite_topic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListTopicDetailActivity : BaseActivityQuiz() {

    private var caterogyId = "0"
    private var caterogyKeyValue = 0
    private var caterogyName = ""
    override fun getLayout(): Int {
        return R.layout.activity_favourite_topic
    }

    override fun initView() {
        imvBack.setOnClickListener { onBackPressed() }
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark)
        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }

    }

    override fun initData() {
        if(intent.hasExtra(ConstantsApp.KEY_CATEROGY_ID)){
            caterogyId = intent.getStringExtra(ConstantsApp.KEY_CATEROGY_ID)
            caterogyName = intent.getStringExtra(ConstantsApp.KEY_CATEROGY_NAME)
            caterogyKeyValue = intent.getIntExtra(ConstantsApp.KEY_CATEROGY_VALUEKEY, 0)
            tvNameOfTopic.text = caterogyName
            showProgessDialog()
            getData()
        }
    }

    private fun getData(){

        RestClient().getRestService().getListTopicFromKey(10,0,caterogyKeyValue,caterogyId).enqueue(object : Callback<RestData<List<Topic>>> {
            override fun onFailure(call: Call<RestData<List<Topic>>>?, t: Throwable?) {
                    dismisProgressDialog()
            }

            override fun onResponse(call: Call<RestData<List<Topic>>>?, response: Response<RestData<List<Topic>>>?) {
               if(response?.body() != null){
                   dismisProgressDialog()
                   val  adapter = FavouriteTopicAdapter(response?.body().data!!, object: HorizontalRecyclerAdapter.OnItemClickListener{
                       override fun onItemClick(view: View?, pTopic: Topic?) {
                           startActivity(Intent(baseContext, TopicDetailActivity::class.java).putExtra(ConstantsApp.KEY_TOPIC_ID,pTopic!!.topic_id))
                       }

                       override fun onItemLongClick(view: View?, position: Int) {

                       }

                   });
                   recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                   recyclerView.adapter = adapter
                   swipeRefreshLayout.isRefreshing = false
                   tvNuberOfTopics.text = response?.body().data!!.size.toString() + " Chủ đề"
               }
            }

        })
    }

    private fun setupView(){

    }
}
