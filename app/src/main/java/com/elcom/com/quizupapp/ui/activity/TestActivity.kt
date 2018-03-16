package com.elcom.com.quizupapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import com.elcom.com.quizupapp.R
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test.*
import java.util.concurrent.Callable

class TestActivity : AppCompatActivity() {

    private val TAG = "RxAndroidSamples"

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        button_run_scheduler.setOnClickListener(View.OnClickListener { onRunSchedulerExampleButtonClicked() })

        Log.d(TAG, "onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    internal fun onRunSchedulerExampleButtonClicked() {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<String>() {
                    override fun onComplete() {
                        Log.d(TAG, "onComplete()")
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "onError()", e)
                    }

                    override fun onNext(string: String) {
                        Log.d(TAG, "onNext($string)")
                    }
                }))
    }

    internal fun sampleObservable(): Observable<String> {
        return Observable.defer {
            // Do some long running operation
//            SystemClock.sleep(5000)
            Observable.just("hailpt", "ambition", "three", "four", "five")
        }
    }
}
