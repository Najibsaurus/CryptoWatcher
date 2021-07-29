package com.najib.cryptowatcher;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.androidnetworking.widget.ANImageView;
import java.util.ArrayList;


public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.BaseViewHolder>
{


    private final ArrayList<Crypto> mCryptoData = new ArrayList<>();
    private onItemClick onItemClick;
    Context mContext;


    public void setOnItemClick(CryptoAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void init(ArrayList<Crypto> data, Context context){
        this.mContext = context;
        mCryptoData.clear();
        mCryptoData.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CryptoAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crypto, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoAdapter.BaseViewHolder holder, int position) {
        holder.bindTo(mCryptoData.get(position), mContext);
        holder.itemView.setOnClickListener(view -> onItemClick.click(mCryptoData.get(position)));
    }

    @Override
    public int getItemCount() {
        return mCryptoData.size();
    }

    public static class BaseViewHolder extends  RecyclerView.ViewHolder{
        final TextView textSymbol,textPrice,textPercentage, textName  ;
        final ANImageView imgLogo;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.ic_coin);
            textSymbol = itemView.findViewById(R.id.txtSymbol);
            textPrice = itemView.findViewById(R.id.txtPrice);
            textPercentage = itemView.findViewById(R.id.txtPercentage);
            textName = itemView.findViewById(R.id.txtName);
        }

        void bindTo(Crypto crypto, Context context){
            textSymbol.setText(crypto.getSymbol());
            textPrice.setText(crypto.getCurrentPrice());
            textPercentage.setText(context.getResources().getString(R.string.percentage, String.format("%s", crypto.getMarketCapChangePercentage24h())));
            textPercentage.setTextColor((crypto.getMarketCapChangePercentage24h() < 0)? Color.RED : Color.rgb(42, 202, 39));
            textName.setText(crypto.getName());
            imgLogo.setImageUrl(crypto.getImage());
        }
    }

    public interface onItemClick{
        void click(Crypto crypto);
    }
}
