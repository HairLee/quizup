package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Hailpt on 3/22/2018.
 */
class Answer : Serializable {

    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("text")
    @Expose
    private var text: String? = null
    @SerializedName("explain")
    @Expose
    private var explain: String? = null
    @SerializedName("published")
    @Expose
    private var published: Int? = null
    @SerializedName("correct")
    @Expose
    private var correct: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getExplain(): String? {
        return explain
    }

    fun setExplain(explain: String) {
        this.explain = explain
    }

    fun getPublished(): Int? {
        return published
    }

    fun setPublished(published: Int?) {
        this.published = published
    }

    fun getCorrect(): String? {
        return correct
    }

    fun setCorrect(correct: String?) {
        this.correct = correct
    }
}