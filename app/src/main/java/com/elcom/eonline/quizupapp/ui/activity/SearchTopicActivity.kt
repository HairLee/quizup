package com.elcom.eonline.quizupapp.ui.activity

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail.Search
import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.eonline.quizupapp.ui.adapter.SearchHorizontalRecyclerAdapter
import com.elcom.eonline.quizupapp.ui.adapter.SearchHomeVerticalRecyclerAdapter
import com.elcom.eonline.quizupapp.ui.adapter.SearchTopicHorizontalRecyclerAdapter
import com.elcom.eonline.quizupapp.ui.adapter.SearchTopicVerticalRecyclerAdapter
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_search_topic2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchTopicActivity : BaseActivityQuiz(), SwipeRefreshLayout.OnRefreshListener, SearchTopicHorizontalRecyclerAdapter.OnItemClickListener {




    override fun onItemClick(view: View?, search: Topic?) {
        if(search!!.topic_id != ""){
            startActivity(Intent(this, TopicDetailActivity::class.java).putExtra(ConstantsApp.KEY_TOPIC_ID,search!!.topic_id))
        }
    }


    override fun getLayout(): Int {
        return R.layout.activity_search_topic2;
    }

    override fun initView() {

        edtSearch.setOnFocusChangeListener { view, b ->
            if (b){
                imvSearch.visibility = View.GONE
                txtSearch.visibility = View.GONE
            } else {
                imvSearch.visibility = View.VISIBLE
                txtSearch.visibility = View.VISIBLE
            }
        }

        edtSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            doSearch(edtSearch.text.toString())
            return@OnEditorActionListener true
        })

        tvSearch.setOnClickListener {
            onBackPressed()
        }

        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun initData() {
        getData("")
    }


    fun getData(keyword:String){

        RestClient().getRestService().searchTopics(keyword,10,0).enqueue(object : Callback<RestData<List<Caterogy>>> {

            override fun onFailure(call: Call<RestData<List<Caterogy>>>?, t: Throwable?) {
                swipeRefreshLayout.isRefreshing = false
                ProgressDialogUtils.dismissProgressDialog()
            }

            override fun onResponse(call: Call<RestData<List<Caterogy>>>?, response: Response<RestData<List<Caterogy>>>?) {
                ProgressDialogUtils.dismissProgressDialog()
                swipeRefreshLayout.isRefreshing = false
                setupView(response!!.body()!!.data!!)
            }
        })

    }

    fun doSearch(keyword:String){
        getData(keyword)
    }



    fun setupView(mList:List<Caterogy>){
        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(false)
        val mAdapter = SearchTopicVerticalRecyclerAdapter(mList, this)
        recycler_view.adapter = mAdapter

        mAdapter.SetOnItemClickListener(this)
    }

    override fun onRefresh() {
        getData("")
    }



    override fun onItemLongClick(view: View?, position: Int) {

    }
}
