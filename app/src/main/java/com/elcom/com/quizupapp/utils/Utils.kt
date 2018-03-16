package com.elcom.com.quizupapp.utils

import android.widget.Button
import android.content.pm.PackageManager
import android.provider.SyncStateContract.Helpers.update
import android.content.pm.PackageInfo
import android.app.Activity
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


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

}