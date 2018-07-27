package com.elcom.com.quizupapp.ui.listener

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Hailpt on 5/3/2018.
 */
interface OnSocketGetOnlineListener {
    fun onUserOnlineByTopic(onlineList:JSONArray)
}