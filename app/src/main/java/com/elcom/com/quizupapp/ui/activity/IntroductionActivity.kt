package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.elcom.com.quizupapp.R
import android.support.v4.app.FragmentPagerAdapter
import com.elcom.com.quizupapp.ui.fragment.IntroductionFragment
import com.elcom.com.quizupapp.ui.activity.IntroductionActivity.MyPagerAdapter
import android.support.v4.view.ViewPager
import com.elcom.com.quizupapp.utils.PreferUtils
import kotlinx.android.synthetic.main.activity_introduction.*


class IntroductionActivity : BaseActivityQuiz() {
    override fun getLayout(): Int {
        return R.layout.activity_introduction;
    }

    override fun initView() {
        val adapterViewPager = MyPagerAdapter(supportFragmentManager)
        vpPager.adapter = adapterViewPager

        tvBegin.setOnClickListener {
            PreferUtils().setFirstTimeGoToApp(this,false)
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun initData() {
    }

    class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        // Returns total number of pages
        override fun getCount(): Int {
            return NUM_ITEMS
        }

        // Returns the fragment to display for that page
        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 // Fragment # 0 - This will show FirstFragment
                -> return IntroductionFragment.newInstance(0, "Page # 1")
                1 // Fragment # 0 - This will show FirstFragment different title
                -> return IntroductionFragment.newInstance(1, "Page # 2")
                2 // Fragment # 1 - This will show SecondFragment
                -> return IntroductionFragment.newInstance(2, "Page # 3")
                else -> return null
            }
        }

        // Returns the page title for the top indicator
        override fun getPageTitle(position: Int): CharSequence? {
            return "Page $position"
        }

        companion object {
            private val NUM_ITEMS = 3
        }

    }
}
