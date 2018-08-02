package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.adapter.FavouriteTopicAdapter
import com.elcom.com.quizupapp.ui.adapter.HorizontalRecyclerAdapter
import com.elcom.com.quizupapp.ui.listener.OnFavouriteListener
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_favourite_topic2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavouriteTopicActivity : BaseActivityQuiz(), OnFavouriteListener {



    override fun getLayout(): Int {
        return R.layout.activity_favourite_topic2
    }

    override fun initView() {
        swipeRefreshLayout.setOnRefreshListener {   getData()  }
        imvBack.setOnClickListener { onBackPressed() }
    }

    override fun initData() {
        showProgessDialog()
        getData()
    }

    override fun onFavourtie(topic: Topic,mFavourite:String) {

            showProgessDialog()
            RestClient().getInstance().getRestService().followAndUnfollowTopic(topic.topic_id!!,mFavourite).enqueue(object: Callback<JsonElement>{
                override fun onFailure(call: Call<JsonElement>?, t: Throwable?) {
                    dismisProgressDialog()
                }

                override fun onResponse(call: Call<JsonElement>?, response: Response<JsonElement>?) {
                    dismisProgressDialog()
                    getData()
                }
            })



    }




    private fun getData(){

        RestClient().getRestService().getListTopicFromKey(10,0,1,"").enqueue(object : Callback<RestData<List<Topic>>> {
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
                    adapter.setOnFavouriteListener(this@FavouriteTopicActivity)
                    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                    recyclerView.adapter = adapter
                    swipeRefreshLayout.isRefreshing = false
                }
            }

        })
    }


}
