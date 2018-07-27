package com.elcom.com.quizupapp.ui.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.fragment.SettingFriendFollowedFragment
import com.elcom.com.quizupapp.ui.fragment.SettingFriendFragment
import kotlinx.android.synthetic.main.activity_setting_friend.*
import java.util.*

class SettingFriendActivity : BaseActivityQuiz() {

    override fun getLayout(): Int {
        return R.layout.activity_setting_friend
    }

    override fun initView() {

    }

    override fun initData() {
        if (viewpager != null) {
            setupViewPager(viewpager)
        }
        tabs.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(supportFragmentManager)
        adapter.addFragment(SettingFriendFollowedFragment(), "Được theo dõi")
        adapter.addFragment(SettingFriendFragment(), "Bạn bè")
        viewPager.adapter = adapter
        imvBack.setOnClickListener { onBackPressed() }
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
