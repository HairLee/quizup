package com.elcom.com.quizupapp.ui.activity.module

import com.elcom.com.quizupapp.ui.activity.ActivityScope
import com.elcom.com.quizupapp.ui.activity.QuizActivity
import com.elcom.com.quizupapp.ui.activity.model.entity.QuizDao
import com.elcom.com.quizupapp.ui.activity.presenter.QuizPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Hailpt on 3/9/2018.
 */
@Module
class QuizActivityModule(private val quizActivity: QuizActivity, private val quizDao: QuizDao) {

    @ActivityScope
    @Provides
    fun provideQuizActivity(): QuizActivity {
        return quizActivity
    }

    @ActivityScope
    @Provides
    fun provideQuizPresenter(): QuizPresenter {
        return QuizPresenter(quizActivity, quizDao)
    }

    @ActivityScope
    @Provides
    fun provideQuizDao(): QuizDao {
        return quizDao
    }
}