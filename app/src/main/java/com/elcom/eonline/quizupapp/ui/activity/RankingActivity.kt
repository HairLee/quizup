package com.elcom.eonline.quizupapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.fragment.MoviesFragment
import com.elcom.eonline.quizupapp.ui.fragment.rating.Rating30Fragment
import com.elcom.eonline.quizupapp.ui.fragment.rating.RatingFriendsFragment
import com.elcom.eonline.quizupapp.ui.fragment.rating.RatingTopFragment
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.ranking_header_item.*
import java.util.ArrayList

class RankingActivity : BaseActivityQuiz() {


    var ratingFriendsFragment =  RatingFriendsFragment()
    var rating30Fragment =  Rating30Fragment()
    var ratingTopFragment =  RatingTopFragment()
    override fun getLayout(): Int {
        return R.layout.activity_ranking
    }

    override fun initView() {
        if (viewpager2 != null) {
            setupViewPager(viewpager2)
        }

        tabs2.setupWithViewPager(viewpager2)

    }

    override fun initData() {

    }

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
