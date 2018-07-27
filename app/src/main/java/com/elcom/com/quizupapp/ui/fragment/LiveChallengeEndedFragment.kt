package com.elcom.com.quizupapp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Cheeses
import com.elcom.com.quizupapp.ui.adapter.LiveChallengeEndedAdapter
import kotlinx.android.synthetic.main.fragment_live_challenge_will_play.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LiveChallengeEndedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_challenge_ended, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = LiveChallengeEndedAdapter(activity!!,
                getRandomSublist(Cheeses.sCheeseStrings, 30))
    }

    private fun getRandomSublist(array: Array<String>, amount: Int): List<String> {
        val list = ArrayList<String>(amount)
        val random = Random()
        while (list.size < amount) {
            list.add(array[random.nextInt(array.size)])
        }
        return list
    }


}
