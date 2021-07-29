package com.najib.cryptowatcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.najib.cryptowatcher.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), OnRefreshListener {

    private lateinit var mAdapter: CryptoAdapter
    private lateinit var viewModel: CryptoViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, NewInstanceFactory()).get(CryptoViewModel::class.java)
        binding.swipeRefresh.setOnRefreshListener(this)
        mAdapter = CryptoAdapter()
        binding.recylerview.layoutManager = LinearLayoutManager(this)
        binding.recylerview.adapter = mAdapter
        viewModel.requestData()
        viewModel.data.observe(this, { items  ->
            if (items.isNotEmpty()) {
                mAdapter.init(items, applicationContext)
                binding.swipeRefresh.isRefreshing = false
            }
        })

        mAdapter.setOnItemClick(object : CryptoAdapter.OnItemClick{
            override fun click(crypto: Crypto?) {
                startActivity(
                    Intent(
                        this@MainActivity,
                        DetailCryptoActivity::class.java
                    ).putExtra(DetailCryptoActivity.EXTRA_CRYPTO, crypto)
                )
            }

        })

    }

    override fun onRefresh() {
        viewModel.requestData()
    }
}