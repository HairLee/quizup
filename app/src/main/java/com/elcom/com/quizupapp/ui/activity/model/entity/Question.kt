package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 3/22/2018.
 */
class Question {

    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("coins")
    @Expose
    private var coins: String? = null
    @SerializedName("point")
    @Expose
    private var point: String? = null
    @SerializedName("user_image_url")
    @Expose
    private var userImageUrl: String? = null
    @SerializedName("question")
    @Expose
    private var question: String? = null
    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("question_image_url")
    @Expose
    private var questionImageUrl: Any? = null
    @SerializedName("answer")
    @Expose
    private var answer: List<Answer>? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getCoins(): String? {
        return coins
    }

    fun setCoins(coins: String) {
        this.coins = coins
    }

    fun getPoint(): String? {
        return point
    }

    fun setPoint(point: String) {
        this.point = point
    }

    fun getUserImageUrl(): String? {
        return userImageUrl
    }

    fun setUserImageUrl(userImageUrl: String) {
        this.userImageUrl = userImageUrl
    }

    fun getQuestion(): String? {
        return question
    }

    fun setQuestion(question: String) {
        this.question = question
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getQuestionImageUrl(): Any? {
        return questionImageUrl
    }

    fun setQuestionImageUrl(questionImageUrl: Any) {
        this.questionImageUrl = questionImageUrl
    }

    fun getAnswer(): List<Answer>? {
        return answer
    }

    fun setAnswer(answer: List<Answer>) {
        this.answer = answer
    }

}