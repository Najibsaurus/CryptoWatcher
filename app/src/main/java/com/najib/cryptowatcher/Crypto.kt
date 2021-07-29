package com.najib.cryptowatcher

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class Crypto : Parcelable {
    var id: String? = null
    var symbol: String? = null
    var name: String? = null
    var image: String? = null
    var currentPrice: String? = null
    var lowPrice: String? = null
    var highPrice: String? = null
    var marketCapChangePercentage24h: Double? = null

    constructor()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(id)
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(currentPrice)
        parcel.writeString(lowPrice)
        parcel.writeString(highPrice)
        if (marketCapChangePercentage24h == null) {
            parcel.writeByte(0.toByte())
        } else {
            parcel.writeByte(1.toByte())
            parcel.writeDouble(marketCapChangePercentage24h!!)
        }
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        symbol = `in`.readString()
        name = `in`.readString()
        image = `in`.readString()
        currentPrice = `in`.readString()
        lowPrice = `in`.readString()
        highPrice = `in`.readString()
        marketCapChangePercentage24h = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readDouble()
        }
    }

    companion object CREATOR : Creator<Crypto> {
        override fun createFromParcel(parcel: Parcel): Crypto {
            return Crypto(parcel)
        }

        override fun newArray(size: Int): Array<Crypto?> {
            return arrayOfNulls(size)
        }
    }

}