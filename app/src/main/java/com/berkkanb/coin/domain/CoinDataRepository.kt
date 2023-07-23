package com.berkkanb.coin.domain

import com.berkkanb.coin.data.model.CoinDataUI
import com.berkkanb.coin.data.model.CoinMarketUI
import retrofit2.Response

interface CoinDataRepository {

    suspend fun getCoinMarketList(): Response<List<CoinMarketUI>>

    suspend fun getCoinDetail(id:String): Response<CoinDataUI>
}