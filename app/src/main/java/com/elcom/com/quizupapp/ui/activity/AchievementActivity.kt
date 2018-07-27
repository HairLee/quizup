package com.elcom.com.quizupapp.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.response.Achivement
import com.elcom.com.quizupapp.ui.activity.model.entity.response.ListDetail
import com.elcom.com.quizupapp.ui.adapter.AchievementAdapter
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import kotlinx.android.synthetic.main.activity_achievement.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AchievementActivity : BaseActivityQuiz(){
    override fun getLayout(): Int {
        return R.layout.activity_achievement
    }

    override fun initView() {

    }

    override fun initData() {
        getData()
    }

    private fun getData(){
        showProgessDialog()
        RestClient().getRestService().getAchievement("1").enqueue(object : Callback<RestData<Achivement>> {
            override fun onFailure(call: Call<RestData<Achivement>>?, t: Throwable?) {
                dismisProgressDialog()
            }

            override fun onResponse(call: Call<RestData<Achivement>>?, response: Response<RestData<Achivement>>?) {
                dismisProgressDialog()
                if (response?.body() != null && response.body().data != null){
                    setupView(response.body()!!.data!!.listDetail!!)
                }

            }
        })
    }

    private fun setupView(topicDetails:List<ListDetail>){
        val mAdapter = AchievementAdapter(topicDetails)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = mAdapter

    }

}
