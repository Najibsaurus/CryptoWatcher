package com.najib.cryptowatcher

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.widget.ANImageView
import com.najib.cryptowatcher.CryptoAdapter.BaseViewHolder
import java.util.*

class CryptoAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val mCryptoData = ArrayList<Crypto?>()
    private var onItemClick: OnItemClick? = null


    var mContext: Context? = null

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
        holder.itemView.setOnClickListener { view: View? ->
            onItemClick?.click(
                mCryptoData[position]
            )
        }
    }

    override fun getItemCount(): Int {
        return mCryptoData.size
    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textSymbol: TextView
        val textPrice: TextView
        val textPercentage: TextView
        val textName: TextView
        val imgLogo: ANImageView
        fun bindTo(crypto: Crypto?, context: Context?) {
            textSymbol.text = crypto?.symbol
            textPrice.text = crypto?.currentPrice
            textPercentage.text = context?.resources?.getString(
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
            color?.let { textPercentage.setTextColor(it) }
            textName.text = crypto?.name
            imgLogo.setImageUrl(crypto?.image)
        }

        init {
            imgLogo = itemView.findViewById(R.id.ic_coin)
            textSymbol = itemView.findViewById(R.id.txtSymbol)
            textPrice = itemView.findViewById(R.id.txtPrice)
            textPercentage = itemView.findViewById(R.id.txtPercentage)
            textName = itemView.findViewById(R.id.txtName)
        }
    }


    interface OnItemClick {
        fun click(crypto: Crypto?)
    }

}