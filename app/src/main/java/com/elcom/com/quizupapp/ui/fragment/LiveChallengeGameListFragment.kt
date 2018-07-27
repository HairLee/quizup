package com.elcom.com.quizupapp.ui.fragment


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.LiveChallengeDetailActivity
import com.elcom.com.quizupapp.ui.activity.LiveChallengeInviteAndPlayGameActivity
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallenge
import com.elcom.com.quizupapp.ui.activity.presenter.LiveChallengeGameListPresenter
import com.elcom.com.quizupapp.ui.adapter.LiveChallengePrepareToPlayAdapter
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.ui.view.LiveChallengeGameListView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
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
class LiveChallengeGameListFragment : Fragment(), View.OnClickListener, LiveChallengeGameListView, SwipeRefreshLayout.OnRefreshListener {



    private var mLiveChallenge =  ArrayList<LiveChallenge>()
    private val mLiveChallengeGameListPresenter =  LiveChallengeGameListPresenter(this)
    private var view: ViewGroup? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(view == null){
            view = inflater!!.inflate(R.layout.fragment_live_challenge_will_play, null) as ViewGroup?
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnInviteFriend.setOnClickListener(this)
        mLiveChallengeGameListPresenter.getData()
        ProgressDialogUtils.showProgressDialog(context, 0, 0)

        refresh.setOnRefreshListener(this)
        refresh.setColorSchemeColors(resources.getColor(R.color.colorPurple), Color.YELLOW, Color.BLUE)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = LiveChallengePrepareToPlayAdapter(activity!!,
                mLiveChallenge, object  : OnItemClickListener{
            override fun onItemClicked(position: Int) {
                goToLiveChallengeDetail(position)
            }

        })
    }

    private fun goToLiveChallengeDetail(pos: Int){
        val bundle = Bundle()
        bundle.putSerializable(ConstantsApp.KEY_LIVE_CHALLENGE_VALUE, mLiveChallenge[pos])
        val intent = Intent(activity, LiveChallengeDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    override fun getListLiveGameSuccessfully(liveChallenge: List<LiveChallenge>) {
        ProgressDialogUtils.dismissProgressDialog()
        mLiveChallenge = liveChallenge as ArrayList<LiveChallenge>
        setupRecyclerView()
        refresh.isRefreshing = false
    }

    override fun getListLiveGameSuccessFail() {
        refresh.isRefreshing = false
        ProgressDialogUtils.dismissProgressDialog()
    }

    override fun onRefresh() {
        mLiveChallengeGameListPresenter.getData()
    }

    override fun onClick(p0: View?) {
        when(p0){
            btnInviteFriend -> {
                startActivity(Intent(activity,LiveChallengeInviteAndPlayGameActivity::class.java))
            }
        }
    }


}
