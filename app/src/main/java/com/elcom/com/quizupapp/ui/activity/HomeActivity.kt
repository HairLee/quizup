package com.elcom.com.quizupapp.ui.activity

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.view.Window
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.R.id.navigation
import com.elcom.com.quizupapp.ui.fragment.MainFragment
import com.elcom.com.quizupapp.ui.fragment.SettingFragment
import com.elcom.com.quizupapp.ui.fragment.TopicFragment
import com.elcom.com.quizupapp.ui.fragment.UserFragment
import com.elcom.com.quizupapp.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_user.*

class HomeActivity : FragmentActivity() {

    var mainFragment:MainFragment? = null
    var userFragment = UserFragment()
    var mEmail:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_home)




        navigation.selectedItemId = R.id.action_item3
//        BottomNavigationViewHelper.removeShiftMode(navigation)
        navigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {

                R.id.action_item1 -> { // to save state of the fragment
                    if(mainFragment == null){
                        mainFragment = MainFragment()
                    }
                    selectedFragment = mainFragment
                }

                R.id.action_item2 -> selectedFragment = TopicFragment()
                R.id.action_item3 -> selectedFragment = userFragment
                R.id.action_item4 -> selectedFragment = SettingFragment()
                R.id.action_item5 -> selectedFragment = MainFragment()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, selectedFragment)
            transaction.commit()
            true


        }

        //Manually displaying the first fragment - one time only
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, userFragment)
        transaction.commit()

        if(intent.hasExtra("EMAIL")){
            mEmail = intent.getStringExtra("EMAIL")
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
