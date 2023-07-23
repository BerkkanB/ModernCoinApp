package com.berkkanb.coin.data.repository

import com.berkkanb.coin.data.api.ApiService
import com.berkkanb.coin.data.model.CoinDataUI
import com.berkkanb.coin.data.model.CoinMarketUI
import com.berkkanb.coin.domain.CoinDataRepository
import retrofit2.Response
import javax.inject.Inject


class CoinDataRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CoinDataRepository {

    override suspend fun getCoinMarketList(): Response<List<CoinMarketUI>> {
        return apiService.getCoinMarketList()
    }

    override suspend fun getCoinDetail(id: String): Response<CoinDataUI> {
        return apiService.getCoinById(id)
    }
}