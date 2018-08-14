package com.elcom.eonline.quizupapp.ui.listener

import org.json.JSONObject

/**
 * Created by Hailpt on 5/3/2018.
 */
interface OnSocketListener {
    fun onSocketConnected()
    fun onAuthentication(content:String)
    fun onCountDown(timeToCountDown:Int)
    fun onResultQuestion(resultQuestion:JSONObject)
}