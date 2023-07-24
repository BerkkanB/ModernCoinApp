package com.berkkanb.coin.data.model

import com.google.gson.annotations.SerializedName

data class MarketChartDataUI(
    @SerializedName("prices") val prices: List<List<Float>>
)
