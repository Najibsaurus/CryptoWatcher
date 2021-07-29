package com.najib.cryptowatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private CryptoAdapter mAdapter;
    private CryptoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CryptoViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recylerview);
        SwipeRefreshLayout swipeRefreshLayout  = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout .setOnRefreshListener(this);
        mAdapter = new CryptoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        viewModel.requestData();

        viewModel.getData().observe(this, items ->{
            if (!items.isEmpty()){
                mAdapter.init(items, getApplicationContext());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        mAdapter.setOnItemClick(crypto -> startActivity(new Intent(this, DetailCryptoActivity.class).putExtra(DetailCryptoActivity.EXTRA_CRYPTO, crypto)));
    }

    @Override
    public void onRefresh() {
        viewModel.requestData();
    }
}