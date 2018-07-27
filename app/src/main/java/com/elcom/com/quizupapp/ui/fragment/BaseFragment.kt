package com.elcom.com.quizupapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.HomeActivity
import com.elcom.com.quizupapp.utils.ProgressDialogUtils

/**
 * Created by Hailpt on 4/23/2018.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun openFragment(newFragment:Fragment) {
        val transaction = fragmentManager!!.beginTransaction()
//        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        transaction.replace(R.id.frame_layout, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name

        val manager = activity!!.getSupportFragmentManager()
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)

        if (!fragmentPopped) { //fragment not in back stack, create it.
            val ft = manager.beginTransaction()
            ft.replace(R.id.frame_layout, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    private fun addHeader(){
        val activity = activity as HomeActivity
        val mView = layoutInflater.inflate(R.layout.search_member_layout, null) as View
        activity.addHeader(mView)
    }

    fun showProgessDialog() {
        ProgressDialogUtils.showProgressDialog(context, 0, 0)
    }

    fun dismisProgressDialog() {
        ProgressDialogUtils.dismissProgressDialog()
    }

}