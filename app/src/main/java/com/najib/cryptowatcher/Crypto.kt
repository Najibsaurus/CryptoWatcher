package com.najib.cryptowatcher



import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Crypto  (
    val id: String?,
    val symbol: String?,
    val name: String?,
    val image: String?,
    val currentPrice: String?,
    val lowPrice: String?,
    val highPrice: String?,
    val marketCapChangePercentage24h: Double?
) : Parcelable