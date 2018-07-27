package com.elcom.com.quizupapp.ui.fragment.search


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.TopicDetailActivity
import com.elcom.com.quizupapp.ui.activity.model.entity.response.CaterogySearch
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Search
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.adapter.SearchHorizontalRecyclerAdapter
import com.elcom.com.quizupapp.ui.adapter.SearchTopicVerticalRecyclerAdapter
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.fragment_search_top.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchTopFragment : Fragment(),SearchHorizontalRecyclerAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {



    private var view: ViewGroup? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (view == null){
            view = inflater!!.inflate(R.layout.fragment_search_top, container, false) as ViewGroup
            ProgressDialogUtils.showProgressDialog(context, 0, 0)
            getData("")

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        getData("")
    }

    fun getData(keyword:String){

        RestClient().getRestService().searchTopic(keyword,10,0,0).enqueue(object : Callback<RestData<List<CaterogySearch>>> {

            override fun onFailure(call: Call<RestData<List<CaterogySearch>>>?, t: Throwable?) {
                swipeRefreshLayout.isRefreshing = false
                ProgressDialogUtils.dismissProgressDialog()
            }

            override fun onResponse(call: Call<RestData<List<CaterogySearch>>>?, response: Response<RestData<List<CaterogySearch>>>?) {
                ProgressDialogUtils.dismissProgressDialog()
                swipeRefreshLayout.isRefreshing = false
                setupView(response!!.body()!!.data!!)
            }
        })

    }

    fun doSearch(keyword:String){
        getData(keyword)
    }

    fun setupView(mList:List<CaterogySearch>){
        val layoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(false)
//        recycler_view.isNestedScrollingEnabled = false

        val mAdapter = SearchTopicVerticalRecyclerAdapter(mList, this)
        recycler_view.adapter = mAdapter

        mAdapter.SetOnItemClickListener(this)
    }

    override fun onItemClick(view: View?, search: Search?) {
        if(search!!.topicId != ""){
            startActivity(Intent(context, TopicDetailActivity::class.java).putExtra(ConstantsApp.KEY_TOPIC_ID,search!!.topicId))
        }

    }

    override fun onItemLongClick(view: View?, position: Int) {

    }


}
