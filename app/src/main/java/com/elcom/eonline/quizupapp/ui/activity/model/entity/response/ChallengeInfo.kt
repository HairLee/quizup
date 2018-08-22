package com.elcom.eonline.quizupapp.ui.activity.model.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 8/22/2018.
 */
class ChallengeInfo {

    @SerializedName("topicId")
    @Expose
    private var topicId: String? = null
    @SerializedName("sendId")
    @Expose
    private var sendId: String? = null
    @SerializedName("toId")
    @Expose
    private var toId: String? = null
    @SerializedName("challenge")
    @Expose
    private var challenge: String? = null
    @SerializedName("url")
    @Expose
    private var url: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("topicName")
    @Expose
    private var topicName: String? = null
    @SerializedName("urlTopic")
    @Expose
    private var urlTopic: String? = null
    @SerializedName("userSendId")
    @Expose
    private var userSendId: String? = null

    fun getTopicId(): String? {
        return topicId
    }

    fun setTopicId(topicId: String) {
        this.topicId = topicId
    }

    fun getSendId(): String? {
        return sendId
    }

    fun setSendId(sendId: String) {
        this.sendId = sendId
    }

    fun getToId(): String? {
        return toId
    }

    fun setToId(toId: String) {
        this.toId = toId
    }

    fun getChallenge(): String? {
        return challenge
    }

    fun setChallenge(challenge: String) {
        this.challenge = challenge
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getTopicName(): String? {
        return topicName
    }

    fun setTopicName(topicName: String) {
        this.topicName = topicName
    }

    fun getUrlTopic(): String? {
        return urlTopic
    }

    fun setUrlTopic(urlTopic: String) {
        this.urlTopic = urlTopic
    }

    fun getUserSendId(): String? {
        return userSendId
    }

    fun setUserSendId(userSendId: String) {
        this.userSendId = userSendId
    }
}