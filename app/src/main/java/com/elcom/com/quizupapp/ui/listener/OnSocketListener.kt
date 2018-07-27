package com.elcom.com.quizupapp.ui.listener

import org.json.JSONObject

/**
 * Created by Hailpt on 5/3/2018.
 */
interface OnSocketListener {
    fun onSocketConnected()
    fun onAuthentication()
    fun onCountDown(timeToCountDown:Int)
    fun onResultQuestion(resultQuestion:JSONObject)

}