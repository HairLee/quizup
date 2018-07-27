package com.elcom.com.quizupapp.ui.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.search_member_layout.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class UserFragment : Fragment(){



    private var view: ViewGroup? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if(view == null){
            view = inflater!!.inflate(R.layout.fragment_user, null) as ViewGroup?
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as HomeActivity
        val myDataFromActivity = activity.mEmail
//        initView(myDataFromActivity)
//        activity.setSupportActionBar(toolbar)
//        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)

        tvCancel.setOnClickListener {
            tvCancel.visibility = View.GONE
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(GamesFragment(), "TOP")
        adapter.addFragment(MoviesFragment(), "PEOPLE")
        adapter.addFragment(TopRatedFragment(), "TOPICS")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

}
