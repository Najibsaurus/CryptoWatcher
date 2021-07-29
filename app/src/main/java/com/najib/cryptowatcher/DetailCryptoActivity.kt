package com.najib.cryptowatcher

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.widget.ANImageView

class DetailCryptoActivity : AppCompatActivity() {

    var logoCrypto: ANImageView? = null
    var symbol: TextView? = null
    var name: TextView? = null
    var price: TextView? = null
    var low: TextView? = null
    var high: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val crypto: Crypto? = intent.getParcelableExtra(EXTRA_CRYPTO)

        logoCrypto = findViewById(R.id.logoCoin)
        symbol = findViewById(R.id.txtSymbolCrypto)
        name = findViewById(R.id.txtNameCrypto)
        price = findViewById(R.id.txtCurrentPrice)
        low = findViewById(R.id.txtLowPrice)
        high = findViewById(R.id.txtHighPrice)

        logoCrypto?.setImageUrl(crypto?.image)
        symbol?.setText(crypto?.symbol)
        name?.setText(crypto?.name)
        price?.setText(crypto?.currentPrice)
        low?.setText(crypto?.lowPrice)
        high?.setText(crypto?.highPrice)
        Log.d(DetailCryptoActivity::class.java.name, "" + crypto?.name)
    }

    companion object {
        const val EXTRA_CRYPTO = "extra_crypto"
    }
}