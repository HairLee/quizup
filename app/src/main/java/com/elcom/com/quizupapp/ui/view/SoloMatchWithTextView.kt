package com.elcom.com.quizupapp.ui.view

import com.elcom.com.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.Question

/**
 * Created by Hailpt on 3/22/2018.
 */
interface SoloMatchWithTextView {
    fun answerTheQuestionSuccess(mData: AnswerQuestion)
    fun  answerTheQuestionFault()
}