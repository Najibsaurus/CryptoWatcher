package com.najib.cryptowatcher

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import java.util.*

class MainActivity : AppCompatActivity(), OnRefreshListener {

    private var mAdapter: CryptoAdapter? = null
    private var viewModel: CryptoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, NewInstanceFactory()).get(
            CryptoViewModel::class.java
        )
        val recyclerView = findViewById<RecyclerView>(R.id.recylerview)
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener(this)
        mAdapter = CryptoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        viewModel?.requestData()

        viewModel?.data?.observe(this, { items  ->
            if (items.isNotEmpty()) {
                mAdapter?.init(items, applicationContext)
                swipeRefreshLayout.isRefreshing = false
            }
        })

        mAdapter?.setOnItemClick(object : CryptoAdapter.OnItemClick{
            override fun click(crypto: Crypto?) {
                startActivity(
                    Intent(
                        this@MainActivity,
                        DetailCryptoActivity::class.java
                    ).putExtra(DetailCryptoActivity.Companion.EXTRA_CRYPTO, crypto)
                )
            }

        })

    }

    override fun onRefresh() {
        viewModel?.requestData()
    }
}