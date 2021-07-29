package com.najib.cryptowatcher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat
import java.util.*

class CryptoViewModel : ViewModel() {
    private val mCryptoData = MutableLiveData<ArrayList<Crypto>>()
    val items = ArrayList<Crypto>()

    fun requestData() {
        items.clear()
        AndroidNetworking.get(BuildConfig.API_URL + "markets")
            .addQueryParameter("vs_currency", "usd")
            .addQueryParameter("order", "market_cap_desc")
            .addQueryParameter("per_page", "50")
            .addQueryParameter("page", "1")
            .addQueryParameter("sparkline", "false")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    var data: JSONObject
                    for (i in 0 until response.length()) {
                        try {
                            data = response.getJSONObject(i)
                            val crypto = Crypto(
                                data.getString("id"),
                                data.getString("symbol").uppercase(),
                                data.getString("name"),
                                data.getString("image"),
                                "$" + DecimalFormat("##.##").format(data.getDouble("current_price")),
                                "$" + DecimalFormat("##.##").format(data.getDouble("high_24h")),
                                "$" + DecimalFormat("##.##").format(data.getDouble("low_24h")),
                                java.lang.Double.valueOf(
                                    DecimalFormat("##.##").format(
                                        data.getDouble("market_cap_change_percentage_24h")
                                    )
                            ))
                            items.add(crypto)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Log.e("Exception error :", e.message.toString())
                        }
                    }
                    mCryptoData.postValue(items)
                }

                override fun onError(error: ANError) {
                    Log.e("Request network error :", error.message.toString())
                }
            })
    }

    val data: LiveData<ArrayList<Crypto>>
        get() = mCryptoData
}