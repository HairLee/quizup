package com.elcom.com.quizupapp

import android.app.Application
import com.elcom.com.quizupapp.ui.activity.QuizActivity
import com.elcom.com.quizupapp.ui.activity.component.QuizComponent
import com.elcom.com.quizupapp.ui.activity.model.entity.QuizDao
import com.elcom.com.quizupapp.ui.activity.module.QuizActivityModule

/**
 * Created by Hailpt on 3/9/2018.
 */
class QuizUpApp : Application() {
//    var quizComponent: QuizComponent? = null
//    fun getQuizComponent(quizActivity: QuizActivity): QuizComponent {
//        quizComponent = DaggerQuizComponent.builder()
//                .quizActivityModule(QuizActivityModule(quizActivity, QuizDao())).build()
//        return quizComponent as QuizComponent
//    }
}