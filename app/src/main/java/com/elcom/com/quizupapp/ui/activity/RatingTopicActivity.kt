package com.elcom.com.quizupapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.response.StatisticalData
import com.elcom.com.quizupapp.ui.activity.model.entity.response.StatisticalRes
import com.elcom.com.quizupapp.ui.fragment.MoviesFragment
import com.elcom.com.quizupapp.ui.fragment.rating.Rating30Fragment
import com.elcom.com.quizupapp.ui.fragment.rating.RatingFriendsFragment
import com.elcom.com.quizupapp.ui.fragment.rating.RatingTopFragment
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ConstantsApp
import kotlinx.android.synthetic.main.activity_rating_topic.*
import kotlinx.android.synthetic.main.ranking_header_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import android.support.design.widget.TabLayout
import android.util.Log


class RatingTopicActivity : BaseActivityQuiz() {


    private var mTopicId = ""
    override fun getLayout(): Int {
        return R.layout.activity_rating_topic
    }

    override fun initView() {
        imvBack.setOnClickListener { onBackPressed() }
    }

    override fun initData() {
        if (viewpager2 != null) {
            setupViewPager(viewpager2)
        }

        tabs2.setupWithViewPager(viewpager2)
        tabs2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                if (statisticalRes != null){
                    when(tab.position){
                        0 ->   ratingFriendsFragment.updateLayout(statisticalRes!!.friends_statistical!!)

                        1 ->   rating30Fragment.updateLayout(statisticalRes!!.monthStatistical!!)

                        2 ->   ratingTopFragment.updateLayout(statisticalRes!!.world_statistical!!)
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        if (intent.hasExtra(ConstantsApp.KEY_QUESTION_ID)){
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            getStatistic()
        }

    }

    var statisticalRes:StatisticalRes? = null
    private fun getStatistic(){

        RestClient().getInstance().getRestService().getStatistic(mTopicId).enqueue(object: Callback<RestData<StatisticalRes>> {
            override fun onResponse(call: Call<RestData<StatisticalRes>>?, response: Response<RestData<StatisticalRes>>?) {
                if (response?.body() != null){
//                    updateLayout(response.body().data!!)
                    statisticalRes = response.body().data!!
                    ratingFriendsFragment.updateLayout(response.body().data!!.friends_statistical!!)
                }
            }

            override fun onFailure(call: Call<RestData<StatisticalRes>>?, t: Throwable?) {

            }
        })
    }

    var ratingFriendsFragment =  RatingFriendsFragment()
    var rating30Fragment =  Rating30Fragment()
    var ratingTopFragment =  RatingTopFragment()

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(supportFragmentManager)
        adapter.addFragment(ratingFriendsFragment, "Bạn bè")
        adapter.addFragment(rating30Fragment, "30 Ngày gần nhất")
        adapter.addFragment(ratingTopFragment, "TOP")
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
