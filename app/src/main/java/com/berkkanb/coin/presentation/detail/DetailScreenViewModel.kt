package com.berkkanb.coin.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkkanb.coin.data.model.CoinDataUI
import com.berkkanb.coin.data.model.MarketChartDataUI
import com.berkkanb.coin.domain.CoinDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val coinDataRepository: CoinDataRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val coinId: String = checkNotNull(savedStateHandle["coinId"])

    private val _uiState = MutableStateFlow(DetailScreenUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getCoinDetails()
    }

    private fun getCoinDetails() {
        viewModelScope.launch {
            setLoadingStatus(true)
            val detailRequest = async { coinDataRepository.getCoinDetail(coinId) }
            val priceRequest = async { coinDataRepository.getMarketChartData(coinId, 7) }

            val detailResponse = detailRequest.await()
            val priceResponse = priceRequest.await()

            if (detailResponse.isSuccessful && priceResponse.isSuccessful) {
                _uiState.update {
                    it.copy(
                        coinDetail = detailResponse.body(),
                        isLoading = false
                    )
                }
                priceResponse.body()?.let { setChartData(it) }
            } else {
                setLoadingStatus(false)
                setHasError()
            }
        }
    }

    private fun setLoadingStatus(status: Boolean) {
        _uiState.update {
            it.copy(
                isLoading = status
            )
        }
    }

    private fun setHasError() {
        _uiState.update {
            it.copy(
                hasError = true
            )
        }
    }

    // TODO NOT AL
    private fun setChartData(priceData: MarketChartDataUI) {
        val chartTimeList = mutableListOf<String>()
        val chartPriceList = mutableListOf<Float>()
        priceData.prices.forEach {
            val price = it[1]
            val time =
                Instant.ofEpochMilli(it.first().toLong()).atZone(ZoneId.systemDefault()).format(
                    DateTimeFormatter.ofPattern("MMM dd")
                )
            chartPriceList.add(price)
            chartTimeList.add(time)
        }
        _uiState.update {
            it.copy(
                priceData = ChartDataUI(chartTimeList, chartPriceList)
            )
        }
    }

}

data class DetailScreenUIState(
    val coinDetail: CoinDataUI? = null,
    val priceData: ChartDataUI? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)

data class ChartDataUI(
    val time: List<String>,
    val price: List<Float>
)