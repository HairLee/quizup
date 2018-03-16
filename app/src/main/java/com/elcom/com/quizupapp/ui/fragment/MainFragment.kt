package com.elcom.com.quizupapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.QuizActivity
import com.elcom.com.quizupapp.ui.adapter.HorizontalRecyclerAdapter
import com.elcom.com.quizupapp.ui.adapter.VerticalRecyclerAdapter
import com.elcom.com.quizupapp.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    fun newInstance(): MainFragment {
        return MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LogUtils.d("hailpt", " onCreate")


    }

    private var view: ViewGroup? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        LogUtils.d("hailpt", " onCreateView")

        if(view == null){
            view = inflater!!.inflate(R.layout.fragment_main, null) as ViewGroup?
        }

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    override fun onResume() {
        super.onResume()


    }

    fun initData(){

        val subList1 = ArrayList<Int>() // Horizontal
        for (i in 10..20) {
            subList1.add(i)
        }


        val list = ArrayList<ArrayList<Int>>() // Vertical
        for (i in 0..23) {
            list.add(subList1)
        }


        recycler_view.setHasFixedSize(false)
        val layoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = layoutManager
        val adapter = VerticalRecyclerAdapter(list)
        recycler_view.adapter = adapter
        adapter.SetOnItemClickListener(object : HorizontalRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
//                Toast.makeText(context, "click " + position, Toast.LENGTH_SHORT).show()
                startActivity(Intent(activity, QuizActivity::class.java));
            }

            override fun onItemLongClick(view: View, position: Int) {
                Toast.makeText(context, "long click " + position, Toast.LENGTH_SHORT).show()
            }
        })
    }



}
