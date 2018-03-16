package com.elcom.com.quizupapp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.fragment_user.*


/**
 * A simple [Fragment] subclass.
 */
class UserFragment : Fragment() {

    private var view: ViewGroup? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        if(view == null){
            view = inflater!!.inflate(R.layout.fragment_user, null) as ViewGroup?
        }



        return view

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as HomeActivity
        val myDataFromActivity = activity.mEmail
        initView(myDataFromActivity)
    }

    fun initView(email:String){
        txt_user_email.text = "Ambition "+email
    }

}// Required empty public constructor
