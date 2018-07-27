package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.elcom.com.quizupapp.ui.activity.presenter.ChallengeFindingOpponentPresenter
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.ChallengeFindingOpponentView
import com.elcom.com.quizupapp.utils.*
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_challenge_fiding_opponent.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeFindingRandomOpponentActivity : BaseActivityQuiz(), ChallengeFindingOpponentView {

    private val mChallengeFindingOpponentPresenter = ChallengeFindingOpponentPresenter(this)
    private var mTopicId = ""
    private var mMatchId = ""

    private var mSocket:Socket? = null

    override fun getLayout(): Int {
        return R.layout.activity_challenge_fiding_opponent
    }

    override fun initView() {

    }

    override fun initData() {
        if (intent.hasExtra(ConstantsApp.KEY_QUESTION_ID)){
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            getMatchIdChallenge()
        }

//        try {
//            mSocket = IO.socket("http://192.168.6.82:3000")
//        } catch (e: URISyntaxException) {
//
//        }
//
//        connectSocket()
    }

    private fun connectSocket(){
        Log.e("TestSocketActivity", " Connecting... ")
        mSocket!!.connect()
        mSocket!!.on("send", onNewSend)
        mSocket!!.on("connect", onNewMessage)
        mSocket!!.on("message", onNewMessage)
    }

    /* 1. Get match Id*/
    private fun getMatchIdChallenge(){
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
        mChallengeFindingOpponentPresenter.getMatchIdChallenge(this, mTopicId)
    }

    /* 2. Get information from match Id*/
    override fun getMatchIdChallengeSuccess(pMatch: JsonElement) {
        ProgressDialogUtils.dismissProgressDialog()
        mMatchId = pMatch.asJsonObject["matchId"].toString()
        mChallengeFindingOpponentPresenter.getData(mTopicId,mMatchId)
    }

    /* 3. Update layout after 2 */
    internal lateinit var countDownTimer: CountDownTimer
    override fun findingOpponentSuccess(pChallengeMatching: ChallengeMatching) {
        AnimationUtil.setAnimationSlideLeft(lnChallengeFinding,false)
        Picasso.get()
                .load(pChallengeMatching.topicImageUrl)
                .into(imvMyself)

        Picasso.get()
                .load(pChallengeMatching.opponent!!.avatar)
                .into(imvOp)

        tvOpName.text = pChallengeMatching.opponent!!.name
        val mainIntent = Intent(this, ChallengeQuestionIntro::class.java)
        countDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {


                val bundle = Bundle()
                bundle.putSerializable("value", pChallengeMatching)
                mainIntent.putExtras(bundle)

                mainIntent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
                mainIntent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
                startActivityForResult(mainIntent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
//            finish()
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }

        }

        countDownTimer.start()



    }

    private val onNewSend = Emitter.Listener { Log.e("TestSocketActivity", " onNewSend ") }

    private val onNewMessage = Emitter.Listener {
        Log.e("TestSocketActivity", " mSocket ")

//        runOnUiThread(Runnable {
//            if (args[0] !is JSONObject) {
//                //                        startActivity(new Intent(TestSocketActivity.this, LoginActivity.class));
//                Utils.showNotify().show(this)
//                return@Runnable
//            }
//            //                    startActivity(new Intent(TestSocketActivity.this, LoginActivity.class));
//            val data = args[0] as JSONObject
//            val username: String
//            val message: String
//            try {
//                username = data.getString("comment")
//                message = data.getString("user")
//            } catch (e: JSONException) {
//                return@Runnable
//            }
//
//            // add the message to view
//            addMessage(username, message)
//        })
    }

    private fun attemptSend() {

        val student1 = JSONObject()
        try {
            student1.put("send_id", 108)
            student1.put("to_id", "Le Thanh Hai")
            student1.put("match_id", "Le Thanh Hai")
            student1.put("topic_id", "Le Thanh Hai")
            student1.put("status_user_bot", "Le Thanh Hai")

        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        mSocket!!.emit("message", student1)
    }

    public override fun onDestroy() {
        super.onDestroy()

//        mSocket!!.disconnect()
//        mSocket!!.off("new message", onNewMessage)
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        countDownTimer.cancel()
        RestClient().getRestService().removeTopicChallenge(mTopicId,mMatchId).enqueue(object: Callback<RestData<JsonElement>> {
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                LogUtils.e("","Remove Ok")
            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY -> {
                when (resultCode) {
                    ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY -> {
                        setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
                        finish()
                    }

                }
            }
        }

    }
}
