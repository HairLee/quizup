package com.elcom.eonline.quizupapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.model.entity.coin.BuyCoin
import com.elcom.eonline.quizupapp.ui.activity.model.entity.coin.Coin
import com.elcom.eonline.quizupapp.ui.activity.model.entity.coin.FreeCoins
import com.elcom.eonline.quizupapp.ui.adapter.CoinBuyAdapter
import com.elcom.eonline.quizupapp.ui.listener.OnItemClickListener
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.utils.WrapContentLinearLayoutManager
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_coin_payment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinPaymentActivity : BaseActivityQuiz(), OnItemClickListener {


    private var buyCoins:List<BuyCoin>? = null
    private var freeCoins: FreeCoins? = null
    override fun getLayout(): Int {
        return R.layout.activity_coin_payment
    }

    override fun initView() {
        imvBack.setOnClickListener {
            onBackPressed()
        }

        rcvFreeCoins.setOnClickListener {
            if(freeCoins != null){
                buyCoin(freeCoins!!.getCoins()!!, "1")
            }

        }
    }

    override fun initData() {
        getData()
    }

    override fun onBackPressed() {
        finish()
    }

    private fun setupFreeCoins(data:List<BuyCoin>){

    }

    private fun setupBuyCoins(data:List<BuyCoin>){
        val coinBuyAdapter = CoinBuyAdapter(data)
        coinBuyAdapter.setOnItemClickListenerr(this)
        rcvBuyCoins.layoutManager = WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvBuyCoins.itemAnimator = DefaultItemAnimator()
        rcvBuyCoins.adapter = coinBuyAdapter
    }

    private fun getData(){
        showProgessDialog()
            RestClient().getInstance().getRestService().getCoinPayment().enqueue(object : Callback<RestData<Coin>> {
                override fun onResponse(call: Call<RestData<Coin>>?, response: Response<RestData<Coin>>?) {
                    dismisProgressDialog()
                   if(response?.body() != null && response.body().data!!.getActive() == 1){
                       val coin = response.body().data
                       tvCoinFreeName.text = coin!!.getFreeCoins()!!.getName()
                       tvCoinFreeDes.text = coin.getFreeCoins()!!.getDescription()
                       tvFreeCoin.text = "+"+ coin.getFreeCoins()!!.getCoins()
                       buyCoins = coin.getBuyCoins()
                       freeCoins = coin.getFreeCoins()
                       setupBuyCoins(buyCoins!!)
                   }
                }

                override fun onFailure(call: Call<RestData<Coin>>?, t: Throwable?) {
                    dismisProgressDialog()
                }


            })
    }

    private fun buyCoin(coin:String, type:String){
        showProgessDialog()
        RestClient().getInstance().getRestService().postBuyCoin(coin,type).enqueue(object: Callback<RestData<JsonElement>>{
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                dismisProgressDialog()

            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                dismisProgressDialog()
                Toast.makeText(this@CoinPaymentActivity, "Nạp Coin thành công",Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onItemClicked(position: Int) {
        if(buyCoins != null){
            buyCoin(buyCoins!!.get(position).getCoins()!!,"2" +
                    "")
        }
    }

}
