package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Person
import com.elcom.com.quizupapp.ui.adapter.SplashAdapter
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.utils.AnimationUtil
import com.elcom.com.quizupapp.utils.LogUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), OnItemClickListener {

    override fun onItemClicked(position: Int) {
        startActivity( LoginActivity::class.java)
    }

    override fun getLayout(): Int {
        return  R.layout.activity_splash
    }

    override fun initView() {
        initListView()
    }

    override fun initData() {

    }

    override fun resumeData() {

    }

    fun initListView(){
//        val rv = findViewById<RecyclerView>(R.id.rcv_splash)

        rcv_splash.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val users = ArrayList<Person>()
        val person1 = Person("Ambition", "Male")

        person1.setBackCamera("ElCom")
        person1.setCompanyName("Ambition")
        users.add(person1)
        users.add(person1)
        users.add(person1)
        users.add(person1)

        val adapter = SplashAdapter(this,users,this)
        rcv_splash.adapter = adapter

    }

}
