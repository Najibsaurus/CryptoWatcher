package com.najib.cryptowatcher;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class CryptoViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Crypto>> mCryptoData = new MutableLiveData<>();
    final ArrayList<Crypto> items = new ArrayList<>();

    void requestData(){
        items.clear();
        AndroidNetworking.get(BuildConfig.API_URL+"markets")
                .addQueryParameter("vs_currency", "usd")
                .addQueryParameter("order", "market_cap_desc")
                .addQueryParameter("per_page", "50")
                .addQueryParameter("page", "1")
                .addQueryParameter("sparkline", "false")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject data;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                data = response.getJSONObject(i);
                                Crypto crypto = new Crypto();
                                crypto.setId(data.getString("id"));
                                crypto.setSymbol(data.getString("symbol").toUpperCase());
                                crypto.setImage(data.getString("image"));
                                crypto.setName(data.getString("name"));
                                crypto.setCurrentPrice("$"+new DecimalFormat("##.##").format(data.getDouble("current_price")));
                                crypto.setHighPrice("$"+new DecimalFormat("##.##").format(data.getDouble("high_24h")));
                                crypto.setLowPrice("$"+new DecimalFormat("##.##").format(data.getDouble("low_24h")));
                                crypto.setMarketCapChangePercentage24h(Double.valueOf(new DecimalFormat("##.##").
                                        format(data.getDouble("market_cap_change_percentage_24h"))));
                                items.add(crypto);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("Exception error :", e.getMessage());
                            }
                        }
                        mCryptoData.postValue(items);
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("Request network error :", error.getMessage());
                    }
                });
    }

    LiveData<ArrayList<Crypto>> getData() {
        return mCryptoData;
    }
}
