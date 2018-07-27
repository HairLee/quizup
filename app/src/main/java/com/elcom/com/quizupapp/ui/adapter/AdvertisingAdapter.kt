package com.elcom.com.quizupapp.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.elcom.com.quizupapp.R
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.main_fragment_adver_item_layout.view.*


/**
 * Created by Hailpt on 3/15/2018.
 */
 class AdvertisingAdapter(context : Context) : PagerAdapter() {


    private val mContext = context
    private var layoutInflater: LayoutInflater? = null
    private val images = arrayOf<Int>(R.drawable.ic_account_box_black_24dp, R.drawable.ic_account_box_black_24dp, R.drawable.ic_account_box_black_24dp)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.main_fragment_adver_item_layout, null)
        view.imageView.setImageResource(images[position])


        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)

    }
}