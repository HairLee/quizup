package com.elcom.com.quizupapp.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.FavouriteTopicActivity
import com.elcom.com.quizupapp.ui.activity.ListTopicDetailActivity
import com.elcom.com.quizupapp.ui.activity.SearchTopicActivity
import com.elcom.com.quizupapp.ui.activity.TopicDetailActivity
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.activity.presenter.HomePresenter
import com.elcom.com.quizupapp.ui.adapter.HorizontalRecyclerAdapter
import com.elcom.com.quizupapp.ui.adapter.ListopicVerticalAdapter
import com.elcom.com.quizupapp.ui.listener.OnSeeMoreCaterogyListener
import com.elcom.com.quizupapp.ui.view.HomeView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.topic_search_member_layout.*
import java.util.*


class ListTopicGroupTotalFragment : BaseFragment(), HomeView, OnSeeMoreCaterogyListener,  SwipeRefreshLayout.OnRefreshListener {



    private val mHomePresenter = HomePresenter(this)
    private var mSearchText = ""
    private var mListCategory = ArrayList<Caterogy>()
    private var mAdapter: ListopicVerticalAdapter? = null
    private var mTopic: Topic? = null

    private fun newInstance(): ListTopicGroupTotalFragment {
        return ListTopicGroupTotalFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LogUtils.d("hailpt", " onCreate")


    }

    private var view: ViewGroup? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(view == null){
            view = inflater!!.inflate(R.layout.fragment_main, null) as ViewGroup
            showProgessDialog()
            mHomePresenter.getLisTopic()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imvSearch.setOnClickListener {
            startActivity(Intent(context, SearchTopicActivity::class.java));
        }

        searchView.setOnQueryTextFocusChangeListener { p0, p1 ->

            if(p1){
                searchView.queryHint = "Tìm kiếm một chủ đề"
                tvAllTopicTitle.visibility = View.INVISIBLE
                imvSearch.visibility = View.INVISIBLE
                viewClose.visibility = View.INVISIBLE
            } else if (mSearchText == "") {
                searchView.queryHint = ""
                tvAllTopicTitle.visibility = View.VISIBLE
                imvSearch.visibility = View.VISIBLE
                viewClose.visibility = View.VISIBLE
            }
        };


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(mAdapter != null){
                    mAdapter!!.filter!!.filter(newText)
                    mSearchText = newText
                }
                return false
            }
        })

        searchView.isIconified = true
        refresh.setOnRefreshListener(this)
        refresh.setColorSchemeColors(resources.getColor(R.color.colorPurple), Color.YELLOW, Color.BLUE)

        imvFavouriteTopic.setOnClickListener {
            startActivity(Intent(context, FavouriteTopicActivity::class.java))
        }
    }


    private fun initData(){

        if (recycler_view == null){
            return
        }


        val layoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(false)
//        recycler_view.isNestedScrollingEnabled = false

        mAdapter = ListopicVerticalAdapter(mListCategory, this)
        recycler_view.adapter = mAdapter

        mAdapter!!.SetOnItemClickListener(object : HorizontalRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, pTopic: Topic?) {
                mTopic = pTopic
                // Go to topic detail
                refreshSearchView()
                startActivity(Intent(activity, TopicDetailActivity::class.java).putExtra(ConstantsApp.KEY_TOPIC_ID,mTopic!!.topic_id))
                activity!!.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)


            }

            override fun onItemLongClick(view: View, position: Int) {
//                Toast.makeText(context, "long click " + position, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun refreshSearchView(){
        searchView.onActionViewCollapsed();
        searchView.setQuery("", false);
        searchView.clearFocus();
    }

    override fun onRefresh() {
        mHomePresenter.getLisTopic()
    }

    override fun onSeeMoreTopics(type:Int, caterogy:Caterogy) {
        startActivity(Intent(context, ListTopicDetailActivity::class.java).putExtra(ConstantsApp.KEY_CATEROGY_VALUEKEY,caterogy.keyValue).putExtra(ConstantsApp.KEY_CATEROGY_ID,caterogy.category_id).putExtra(ConstantsApp.KEY_CATEROGY_NAME,caterogy.name))
    }

    override fun getListTopicSuccess(pData: List<Caterogy>) {
        refresh.isRefreshing = false
        dismisProgressDialog()
        mListCategory = pData as ArrayList<Caterogy>
        initData()
    }

    override fun getListTopicFail() {
        if (context == null){

        }
    }

}
