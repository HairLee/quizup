package com.elcom.com.quizupapp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.dialog.ChallengeInventationDialog
import com.elcom.com.quizupapp.ui.listener.OnDialogInvitationListener
import kotlinx.android.synthetic.main.item_invented_friend_layout.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NotificationFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    var mChallengeGameDialog: ChallengeInventationDialog? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCancel.setOnClickListener {
            Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show()

        }

        btnPlay.setOnClickListener {
            Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show()
        }

        btnChallenge.setOnClickListener {
            mChallengeGameDialog = ChallengeInventationDialog(context!!, object : OnDialogInvitationListener {

                override fun onInviteFriendToPlayGame() {
                    Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show()
                }

                override fun onCancelInviteFriendToPlayGame() {
                    Toast.makeText(context, "Reject", Toast.LENGTH_SHORT).show()
                }

            })
            mChallengeGameDialog!!.show()
        }

    }


}
