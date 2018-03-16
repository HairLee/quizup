package com.elcom.com.quizupapp.ui.activity.model

import android.util.Log
import com.elcom.com.quizupapp.ui.activity.MainActivity
import com.elcom.com.quizupapp.ui.activity.model.entity.Person
import com.elcom.com.quizupapp.ui.network.RestClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by admin on 3/8/2018.
 */
class MainIteractor(var mMainListener: MainListener) {

    private val disposable = CompositeDisposable()

    fun getData() {
//        doGetJson()
        testJavaRx2()

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

    fun testJavaRx(){
        disposable.add(
                RestClient().getInstance().getRestService().testJavaRx()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(object : Function<List<Person>, List<Person>> {
                            @Throws(Exception::class)
                            override fun apply(notes: List<Person>): List<Person> {
                                // TODO - note about sort
                                Collections.sort<Person>(notes) { n1, n2 -> n2.getId() - n1.getId() }
                                return notes
                            }
                        })
                        .subscribeWith(object : DisposableSingleObserver<List<Person>>() {
                            override fun onSuccess(notes: List<Person>) {


                            }

                            override fun onError(e: Throwable) {

                            }
                        })
        )
    }

    fun testJavaRx2(){
        disposable.add(
                RestClient().getInstance().getRestService().testJavaRx2()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<Person>() {
                            override fun onSuccess(t: Person) {

                                mMainListener.onPushDataListener("Ambition " +t.getCompanyName())
                            }


                            override fun onError(e: Throwable) {

                            }
                        })

        )

    }


}


