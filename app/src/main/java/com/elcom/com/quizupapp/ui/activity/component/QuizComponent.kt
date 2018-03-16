package com.elcom.com.quizupapp.ui.activity.component

import com.elcom.com.quizupapp.ui.activity.ActivityScope
import com.elcom.com.quizupapp.ui.activity.QuizActivity
import com.elcom.com.quizupapp.ui.activity.module.QuizActivityModule
import com.elcom.com.quizupapp.ui.activity.presenter.QuizPresenter
import dagger.Component

/**
 * Created by Hailpt on 3/9/2018.
 */
@ActivityScope
@Component(modules = arrayOf(QuizActivityModule::class))
interface QuizComponent {
    fun inject(quizActivity: QuizActivity): QuizActivity

    fun presenter(): QuizPresenter
}