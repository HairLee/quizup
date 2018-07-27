package com.elcom.com.quizupapp.utils

import android.app.Activity
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.util.Base64
import android.util.Log
import android.widget.Button
import com.elcom.com.quizupapp.R
import com.facebook.AccessToken
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import android.app.NotificationManager
import com.elcom.com.quizupapp.R.mipmap.ic_launcher
import android.app.PendingIntent
import android.support.v4.app.NotificationCompat
import com.elcom.com.quizupapp.ui.activity.HomeActivity
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.AlphaAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView


/**
 * Created by Hailpt on 3/13/2018.
 */
class Utils {

    class CustomButtom(pButtonList:List<Button>){
        var mButtonList = pButtonList
        fun enableButtonClick() {
            for (button in mButtonList) {
                button.setEnabled(true)
            }
        }

        fun unableButtonClick() {
            for (button in mButtonList) {
                button.setEnabled(false)
            }
        }

        fun changeColorWithCorrectAnswer(mAnswer:Int,mCorrectAnswer:Int){
            for (i in 0 until mButtonList.size) {
                if (i == mAnswer && mAnswer == mCorrectAnswer){
                    mButtonList[i].setBackgroundResource(R.drawable.playing_game_right_bg)
                    mButtonList[i].setTextColor(Color.WHITE)
//                    doAnimationForImageview(mButtonList[i])
                    return
                } else {
                    mButtonList[mAnswer].setBackgroundResource(R.drawable.playing_game_wrong_bg)
                    mButtonList[mAnswer].setTextColor(Color.WHITE)
                }
            }
        }

        fun doAnimationForImageview(imageView:Button){
            val animation = AlphaAnimation(1f, 0f)
            animation.duration = 1000
            animation.interpolator = LinearInterpolator()
            animation.repeatCount = Animation.INFINITE
            animation.repeatMode = Animation.REVERSE
            imageView.startAnimation(animation)
        }

        fun refreshColorAfterTheAnswer(){
            for (i in 0 until mButtonList.size) {
                mButtonList[i].setBackgroundResource(R.drawable.playing_game_normal_bg)
                mButtonList[i].setTextColor(Color.BLACK)
            }
        }
    }

    fun setFontForText(context:Context,tv:TextView){
        val face = Typeface.createFromAsset(context.assets,
                "fonts/HelveticaLightOblique.ttf")
        tv.typeface = face
    }

    fun setFontForButton(context:Context,tv:Button){
        val face = Typeface.createFromAsset(context.assets,
                "fonts/HelveticaLightOblique.ttf")
        tv.typeface = face
    }

    fun printKeyHash(context: Activity): String? {
        val packageInfo: PackageInfo
        var key: String? = null
        try {
            //getting application package name, as defined in manifest
            val packageName = context.applicationContext.packageName

            //Retriving package info
            packageInfo = context.packageManager.getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES)

            Log.e("Package Name=", context.applicationContext.packageName)

            for (signature in packageInfo.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                key = String(Base64.encode(md.digest(), 0))

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key)
            }
        } catch (e1: PackageManager.NameNotFoundException) {
            Log.e("Name not found", e1.toString())
        } catch (e: NoSuchAlgorithmException) {
            Log.e("No such an algorithm", e.toString())
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }

        return key
    }

    class startActivity(){

        fun begin(intent: Intent, mContext: Context) {
            if(mContext is Activity){
                mContext.startActivity(intent)
                mContext.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }


    class socialNetWork(){
        fun isLoginedFacebook(): Boolean {
            val accessToken = AccessToken.getCurrentAccessToken()
            return accessToken != null
        }
    }

    class showNotify(){

        fun show(ctx:Context){
            val intent = Intent(ctx, HomeActivity::class.java)
            val contentIntent = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val b = NotificationCompat.Builder(ctx)

            b.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_favorite_blue_300_24dp)
                    .setTicker("Hearty365")
                    .setContentTitle("Default notification")
                    .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
                    .setContentIntent(contentIntent)
                    .setContentInfo("Info")


            val notificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, b.build())
        }
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm!!.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }



}



