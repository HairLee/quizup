package com.elcom.eonline.quizupapp.ui.activity.model

import com.elcom.eonline.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.eonline.quizupapp.ui.activity.model.entity.Question

/**
 * Created by Hailpt on 3/22/2018.
 */
interface SoloMatchWithTextListener {
    fun answerTheQuestionSuccess(mData: AnswerQuestion)
    fun  answerTheQuestionFault()
}