package com.elcom.com.quizupapp.ui.activity.model

import android.util.Log
import com.elcom.com.quizupapp.ui.activity.MainActivity
import com.elcom.com.quizupapp.ui.activity.model.entity.Person
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.utils.LogUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import io.reactivex.observers.DisposableObserver



/**
 * Created by admin on 3/8/2018.
 */
class MainIteractor(var mMainListener: MainListener) {

    private val disposable = CompositeDisposable()

    fun getData() {
//        doGetJson()

    }

    private fun doGetJson() {

        RestClient().getInstance().getRestService().findIdByPhone().enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {

                mMainListener.onPushDataListener("Ambition " +response.body().getCompanyName())
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {


            }
        })
    }

    var TAG = "AMBITION"

    var animalsObserver1 = getAnimalsObserver()
    private fun getAnimalsObserver(): DisposableObserver<Person> {
        return object : DisposableObserver<Person>() {

            override fun onNext(s: Person) {
                Log.d(TAG, "Name: " + s.getCompanyName())
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "All items are emitted!")
            }
        }
    }





}


