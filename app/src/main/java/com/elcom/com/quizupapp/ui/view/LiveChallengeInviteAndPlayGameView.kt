package com.elcom.com.quizupapp.ui.view

import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveQuestionData
import com.google.gson.JsonElement

/**
 * Created by Hailpt on 4/27/2018.
 */
interface LiveChallengeInviteAndPlayGameView {
    fun getChallengeQuestionListSuccessfully(challengeQuestions:List<ChallengeQuestion>)
    fun getChallengeQuestionListFault()

    fun getChallengeQuestionSuccessfully(challengeQuestions:LiveQuestionData)
    fun getChallengeQuestionFault()

    fun getTimeCountDownQuestionSuccessfully(timeCountDown:Long)
    fun getTimeCountDownChallengeQuestionFault()



    fun getResultAfterAnsweringTheQuestion(data:JsonElement)

    fun breakLiveChallengeQuestion(data:JsonElement)

    fun endLiveChallengeGameSuccessfully(data:JsonElement)
    fun endLiveChallengeGameFault()

}