package com.elcom.eonline.quizupapp.utils

import android.content.Context

/**
 * Created by Hailpt on 3/21/2018.
 */
class PreferUtils {

    private val PREFER_NAME = "quizup"
    private val PREFER_GCM_TOKEN = "gcm_token"
    private val KEY_FIRST_TIME = "KEY_FIRST_TIME"
    private val KEY_USER_NAME= "KEY_USER_NAME"
    private val PREFER_AVATAR_MYSELF = "PREFER_AVATAR_MYSELF"
    private val USER_ID = "user_id"
    private val CHANLLENGE_TIME_TO_INTIVE = "CHANLLENGE_TIME_TO_INTIVE"
    private val FACEBOOK_ID = "FACEBOOK_ID"
    private val KEY_ENCRYPTION = "kenc"
    private val KEY_SOUND_SETTING = "KEY_SOUND_SETTING"
    private val KEY_ADMOD_COUNT = "KEY_ADMOD_COUNT"

//    private val applicationContext  = QuizUpApp().getAppContext()!!

    fun setName(context: Context, token: String) {
        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(KEY_USER_NAME, token)
        editor.commit()
    }

    fun getName(context: Context): String {
        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(KEY_USER_NAME, "")
    }


    fun setToken(context: Context, token: String) {
        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(PREFER_GCM_TOKEN, token)
        editor.commit()
    }

    fun getToken(context: Context): String {
        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(PREFER_GCM_TOKEN, "")
    }

    fun setAvatar(context: Context, token: String) {
        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(PREFER_AVATAR_MYSELF, token)
        editor.commit()
    }

    public  fun getAvatar(context: Context): String {
        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(PREFER_AVATAR_MYSELF, "")
    }

    fun setUserId(context: Context, token: String) {
        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(USER_ID, token)
        editor.commit()
    }

    fun getUserId(context: Context): String {
        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(USER_ID, "")
    }


    fun setChallengeTimeToInviteFriend(context: Context, time: String) {
        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(CHANLLENGE_TIME_TO_INTIVE, time)
        editor.commit()
    }

    fun getChallengeTimeToInviteFriend(context: Context): String {
        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(CHANLLENGE_TIME_TO_INTIVE, "")
    }



    fun setFacebookId(context: Context, time: String) {
        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(FACEBOOK_ID, time)
        editor.commit()
    }

    fun getFacebookId(context: Context): String {
        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(FACEBOOK_ID, "")
    }



    fun setFirstTimeGoToApp(context: Context, token: Boolean) {
        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_FIRST_TIME, token)
        editor.commit()
    }

    fun getFirstTimeGoToApp(context: Context): Boolean {
        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(KEY_FIRST_TIME,true)
    }

    fun setSoundSetting(context: Context, token: Boolean) {
        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_SOUND_SETTING, token)
        editor.commit()
    }

    fun getSoundSetting(context: Context): Boolean {
        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(KEY_SOUND_SETTING,false)
    }


//    fun setAdmodCount(context: Context, token: Int) {
//        val editor = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE).edit()
//        editor.putInt(KEY_ADMOD_COUNT, token)
//        editor.commit()
//    }
//
//    fun getAdmodCount(context: Context): Int {
//        val preferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)
//        return preferences.getInt(KEY_ADMOD_COUNT,0)
//    }

}