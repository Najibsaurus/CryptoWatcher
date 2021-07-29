package com.najib.cryptowatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

public class DetailCryptoActivity extends AppCompatActivity {

    public static final String EXTRA_CRYPTO = "extra_crypto";
    ANImageView logoCrypto;
    TextView symbol,name, price, low, high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Crypto crypto = getIntent().getParcelableExtra(EXTRA_CRYPTO);
        logoCrypto = findViewById(R.id.logoCoin);
        symbol = findViewById(R.id.txtSymbolCrypto);
        name = findViewById(R.id.txtNameCrypto);
        price = findViewById(R.id.txtCurrentPrice);
        low = findViewById(R.id.txtLowPrice);
        high = findViewById(R.id.txtHighPrice);

        logoCrypto.setImageUrl(crypto.getImage());
        symbol.setText(crypto.getSymbol());
        name.setText(crypto.getName());
        price.setText(crypto.getCurrentPrice());
        low.setText(crypto.getLowPrice());
        high.setText(crypto.getHighPrice());

        Log.d(DetailCryptoActivity.class.getName(),""+crypto.getName());
    }
}