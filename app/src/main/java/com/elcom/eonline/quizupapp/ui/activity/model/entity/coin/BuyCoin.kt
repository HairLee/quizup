package com.elcom.eonline.quizupapp.ui.activity.model.entity.coin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 8/28/2018.
 */
class BuyCoin {

    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("description")
    @Expose
    private var description: String? = null
    @SerializedName("coins")
    @Expose
    private var coins: String? = null
    @SerializedName("vnd")
    @Expose
    private var vnd: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getCoins(): String? {
        return coins
    }

    fun setCoins(coins: String) {
        this.coins = coins
    }

    fun getVnd(): String? {
        return vnd
    }

    fun setVnd(vnd: String) {
        this.vnd = vnd
    }
}