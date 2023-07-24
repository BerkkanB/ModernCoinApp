package com.berkkanb.coin.data.api

import com.berkkanb.coin.data.constants.CurrencyType
import com.berkkanb.coin.data.model.CoinDataUI
import com.berkkanb.coin.data.model.CoinMarketUI
import com.berkkanb.coin.data.model.MarketChartDataUI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins/markets")
    suspend fun getCoinMarketList(
        @Query("vs_currency") vs_currency: String = CurrencyType.USD.name

    ): Response<List<CoinMarketUI>>

    @GET("coins/{id}")
    suspend fun getCoinById(
        @Path("id") coinId: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false
    ): Response<CoinDataUI>

    @GET("coins/{id}/market_chart")
    suspend fun getMarketChart(
        @Path("id") id: String,
        @Query("vs_currency") currency: String = CurrencyType.USD.name,
        @Query("days") days: Int,
        @Query("interval") interval: String = "daily",
    ): Response<MarketChartDataUI>

}