package com.elcom.com.quizupapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Result
import com.elcom.com.quizupapp.ui.activity.model.entity.response.Statistical
import com.elcom.com.quizupapp.ui.activity.model.entity.response.StatisticalData
import com.elcom.com.quizupapp.ui.fragment.MoviesFragment
import com.elcom.com.quizupapp.ui.fragment.StatisticWorldFragment
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.PreferUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_solo_match_statistic.*
import kotlinx.android.synthetic.main.item_activity_result_top_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class SoloMatchStatisticActivity : BaseActivityQuiz() {


    private var mMatchId = "1"
    private var mTopicId = "1"
    private var statisticWorldFragment = StatisticWorldFragment()

    private var mWorldList = listOf<Statistical>()
    override fun getLayout(): Int {
        return R.layout.activity_solo_match_statistic
    }

    override fun initView() {
        imvClose.setOnClickListener {
            setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
            finish()
        }
    }

    override fun initData() {

        if (viewpager2 != null) {
            setupViewPager(viewpager2)
        }
        tabs2.setupWithViewPager(viewpager2)

        tabs2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0->{
                        statisticWorldFragment.setData(mWorldList)
                    }
                }
            }

        })
        if(intent.hasExtra(ConstantsApp.KEY_SOLO_MATCH_ID)){
            mMatchId = intent.getStringExtra(ConstantsApp.KEY_SOLO_MATCH_ID)
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            getStatistic()
        }
    }

    override fun onBackPressed() {
        imvClose.performClick()
    }

    private fun getStatistic(){

        RestClient().getInstance().getRestService().getStatistic(mTopicId,mMatchId).enqueue(object: Callback<RestData<StatisticalData>> {
            override fun onResponse(call: Call<RestData<StatisticalData>>?, response: Response<RestData<StatisticalData>>?) {
                if (response?.body() != null){
                    updateLayout(response.body().data!!)
                }
            }

            override fun onFailure(call: Call<RestData<StatisticalData>>?, t: Throwable?) {

            }
        })
    }

    fun updateLayout(pResult:StatisticalData){

        tvX.text = pResult.kMultiple.toString()
        tvScore.text = "Chuỗi thắng : " + pResult.winString
        tvLevel.text = "Level " + pResult.level
        tvTitleTopic.text = pResult.topicName
        tvComment.text = pResult.comment
        tvScoreWin.text = pResult.winPoints.toString()
        tvTotalScore.text = pResult.bonusPoints.toString()
        tvTotalXp.text = pResult.totalXp.toString()


        Picasso.get()
                .load(pResult.userAvarta)
                .resize(300, 300)
                .centerCrop()
                .into(imvTopic)

        mWorldList = pResult.world_statistical!!
        statisticWorldFragment.setData(pResult.world_statistical!!)

        Log.e("hailpt"," statisticWorldFragment ~~~> "+ pResult.world_statistical!!.size)
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(supportFragmentManager)
        adapter.addFragment(statisticWorldFragment, "Bạn bè")
        adapter.addFragment(MoviesFragment(), "Thế giới")
        adapter.addFragment(MoviesFragment(), "Quốc gia")
        viewPager.adapter = adapter
    }

    internal class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val mFragments = ArrayList<Fragment>()
        private val mFragmentTitles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            Log.e("hailpt"," setupViewPager "+mFragments[position].tag)
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitles[position]
        }
    }

}
