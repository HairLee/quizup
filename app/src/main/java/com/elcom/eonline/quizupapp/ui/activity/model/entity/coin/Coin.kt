package com.elcom.eonline.quizupapp.ui.activity.model.entity.coin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 8/28/2018.
 */
class Coin {

    @SerializedName("active")
    @Expose
    private var active: String? = null
    @SerializedName("free_coins")
    @Expose
    private var freeCoins: FreeCoins? = null
    @SerializedName("buy_coins")
    @Expose
    private var buyCoins: List<BuyCoin>? = null

    fun getActive(): String? {
        return active
    }

    fun setActive(active: String) {
        this.active = active
    }

    fun getFreeCoins(): FreeCoins? {
        return freeCoins
    }

    fun setFreeCoins(freeCoins: FreeCoins) {
        this.freeCoins = freeCoins
    }

    fun getBuyCoins(): List<BuyCoin>? {
        return buyCoins
    }

    fun setBuyCoins(buyCoins: List<BuyCoin>) {
        this.buyCoins = buyCoins
    }
}