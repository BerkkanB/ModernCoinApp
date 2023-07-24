package com.berkkanb.coin.data.model

import com.google.gson.annotations.SerializedName

data class CoinDataUI(
    val id: String,
    val symbol: String,
    val name: String,
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String,
    val description: Description,
    val image: Image,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double
)


data class Description(
    val en: String
)

data class Image(
    val thumb: String,
    val small: String,
    val large: String
)
