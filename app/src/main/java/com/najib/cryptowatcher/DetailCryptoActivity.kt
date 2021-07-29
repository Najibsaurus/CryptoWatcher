package com.najib.cryptowatcher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.najib.cryptowatcher.databinding.ActivityDetailBinding

class DetailCryptoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val crypto: Crypto? = intent.getParcelableExtra(EXTRA_CRYPTO)

        binding.logoCoin.setImageUrl(crypto?.image)
        binding.txtSymbolCrypto.text = crypto?.symbol
        binding.txtNameCrypto.text = crypto?.name
        binding.txtCurrentPrice.text = crypto?.currentPrice
        binding.txtLowPrice.text = crypto?.lowPrice
        binding.txtHighPrice.text = crypto?.highPrice

        Log.d(DetailCryptoActivity::class.java.name, "" + crypto?.name)
    }

    companion object {
        const val EXTRA_CRYPTO = "extra_crypto"
    }
}