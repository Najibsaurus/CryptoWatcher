package com.najib.cryptowatcher

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.najib.cryptowatcher.CryptoAdapter.BaseViewHolder
import com.najib.cryptowatcher.databinding.ItemCryptoBinding
import java.util.*

class CryptoAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val mCryptoData = ArrayList<Crypto?>()
    private var onItemClick: OnItemClick? = null


    private var mContext: Context? = null

    fun setOnItemClick(onItemClick:OnItemClick) {
        this.onItemClick = onItemClick    }

    fun init(data: ArrayList<Crypto>?, context: Context?) {
        mContext = context
        mCryptoData.clear()
        data?.let { mCryptoData.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crypto, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindTo(mCryptoData[position], mContext)
        holder.itemView.setOnClickListener {
            onItemClick?.click(
                mCryptoData[position]
            )
        }
    }

    override fun getItemCount(): Int {
        return mCryptoData.size
    }

  inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCryptoBinding.bind(itemView)

        fun bindTo(crypto: Crypto?, context: Context?) {
            binding.txtSymbol.text = crypto?.symbol
            binding.txtPrice.text = crypto?.currentPrice
            binding.txtPercentage.text = context?.resources?.getString(
                R.string.percentage,
                String.format("%s", crypto?.marketCapChangePercentage24h)
            )
            val color   =  crypto?.marketCapChangePercentage24h?.let {
                if (it < 0) {
                    Color.RED
                }
                else {
                    Color.rgb(
                        42,
                        202,
                        39
                    )
                }
            }
            color?.let { binding.txtPercentage.setTextColor(it) }
            binding.txtName.text = crypto?.name
            binding.icCoin.setImageUrl(crypto?.image)
        }
    }


    interface OnItemClick {
        fun click(crypto: Crypto?)
    }

}