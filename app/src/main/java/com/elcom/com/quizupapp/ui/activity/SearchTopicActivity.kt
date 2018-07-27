package com.elcom.com.quizupapp.ui.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.fragment.LiveChallengeEndedFragment
import com.elcom.com.quizupapp.ui.fragment.search.SearchPlayerFragment
import com.elcom.com.quizupapp.ui.fragment.search.SearchTopFragment
import com.elcom.com.quizupapp.ui.fragment.search.SearchTopicFragment
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.utils.Utils
import kotlinx.android.synthetic.main.activity_search_topic.*
import java.util.ArrayList
import android.view.inputmethod.EditorInfo
import android.widget.TextView



class SearchTopicActivity : BaseActivityQuiz(), OnItemClickListener {



    private var itemSelected = 0;
    private var searchTopFragment = SearchTopFragment()
    private var searchPlayerFragment = SearchPlayerFragment()
    private var searchTopicFragment = SearchTopicFragment()
    override fun getLayout(): Int {
        return R.layout.activity_search_topic
    }

    override fun initView() {
        tvSearch.setOnClickListener {
            onBackPressed()
        }

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
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Utils().hideKeyboard(this,lnRoot)
                when(viewpager2.currentItem){
                    0 -> {
                        searchTopFragment.doSearch(edtSearch.text.toString())
                    }

                    1-> {
                        searchPlayerFragment.doSearch(edtSearch.text.toString())
                    }

                    2->{
                        searchTopicFragment.doSearch(edtSearch.text.toString())
                    }
                }

                if(viewpager2.currentItem == 0){
                    Utils().hideKeyboard(this,lnRoot)
                    searchTopFragment.doSearch(edtSearch.text.toString())
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun initData() {
        if (viewpager2 != null) {
            setupViewPager(viewpager2)
        }

        tabs2.setupWithViewPager(viewpager2)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(supportFragmentManager)
        adapter.addFragment(searchTopFragment, "TOP")
        adapter.addFragment(searchPlayerFragment, "NGƯỜI CHƠI")
        adapter.addFragment(searchTopicFragment, "CHỦ ĐỀ")
        adapter.setOnItemSelected(this)
        viewPager.adapter = adapter

        viewPager
    }

    internal class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private var onItemSelected:OnItemClickListener? = null
        private val mFragments = ArrayList<Fragment>()
        private val mFragmentTitles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            onItemSelected!!.onItemClicked(position)
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitles[position]
        }

        fun setOnItemSelected(onItemSelected:OnItemClickListener){
            this.onItemSelected = onItemSelected
        }
    }

    override fun onItemClicked(position: Int) {
        Log.e("hailpt"," position "+position)
    }




}
