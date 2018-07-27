package com.elcom.com.quizupapp.ui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.SoloMatch
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.adapter.HistoryListRecyclerAdapter
import com.elcom.com.quizupapp.ui.listener.OnHistoryListListener
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.PreferUtils
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_history_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HistoryListActivity : BaseActivityQuiz() {

    private var mList = listOf<Topic>()
    override fun getLayout(): Int {
        return R.layout.activity_history_list
    }

    override fun initView() {
        imvBack.setOnClickListener {
            setResult(999)
            finish() }

        val builder = AlertDialog.Builder(this)

        tvRemoveAll.setOnClickListener {
            builder.setMessage("Xóa tất cả lịch sử game?")
            builder.setPositiveButton("Xóa", DialogInterface.OnClickListener { dialog, which ->
                removeAllHistory()
                dialog.dismiss()
            })

            builder.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
                // Do nothing
                dialog.dismiss()
            })

            val alert = builder.create()
            alert.show()
        }


    }

    private fun removeAllHistory(){
        RestClient().getRestService().removeAllHistory("").enqueue(object : Callback<RestData<JsonElement>>{
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                setResult(999)
                finish()
            }
        })
    }

    override fun initData() {
        getData()
    }

    private fun getData(){
        showProgessDialog()
        RestClient().getInstance().getRestService().getListTopicFromKey(10,0,2,"2").enqueue(object : Callback<RestData<List<Topic>>> {

            override fun onFailure(call: Call<RestData<List<Topic>>>?, t: Throwable?) {
                ProgressDialogUtils.dismissProgressDialog()
            }

            override fun onResponse(call: Call<RestData<List<Topic>>>?, response: Response<RestData<List<Topic>>>?) {
                ProgressDialogUtils.dismissProgressDialog()
                if(response?.body() != null && response.body().data != null){
                    val mAdapter =  HistoryListRecyclerAdapter( response.body().data, object : OnHistoryListListener {

                        override fun onRemoveHistory(topic: Topic) {

                        }

                        override fun onPlayTopicAgain(topic: Topic) {
                            getMatchId(topic.topic_id.toString())
                        }

                    })

                    recyclerView.layoutManager = LinearLayoutManager(baseContext)
                    recyclerView.setHasFixedSize(false)
                    recyclerView.adapter = mAdapter
                }

            }

        })
    }

    fun getMatchId(pTopicId:String){

        showProgessDialog()
        RestClient().getInstance().getRestService().getTopicMatchId(PreferUtils().getUserId(this), pTopicId).enqueue(object : Callback<RestData<SoloMatch>>{
            override fun onResponse(call: Call<RestData<SoloMatch>>?, response: Response<RestData<SoloMatch>>?) {
                ProgressDialogUtils.dismissProgressDialog()
                if (response?.body() != null){
                    var intent = Intent(applicationContext, SoloQuestionIntro::class.java)
                    intent.putExtra(ConstantsApp.KEY_QUESTION_ID,response.body().data!!.topicId)
                    intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,response.body().data!!.id)
                    startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

            override fun onFailure(call: Call<RestData<SoloMatch>>?, t: Throwable?) {

            }

        })

    }


}
