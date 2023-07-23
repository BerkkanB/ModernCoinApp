package com.berkkanb.coin.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkkanb.coin.data.model.CoinMarketUI
import com.berkkanb.coin.domain.CoinDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val coinDataRepository: CoinDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getCoinMarketData()
    }

    private fun getCoinMarketData() {
        viewModelScope.launch {
            setLoadingStatus(true)
            val response = coinDataRepository.getCoinMarketList()
            if (response.isSuccessful){
                _uiState.update {
                    it.copy(
                        coinList = response.body(),
                        isLoading = false
                    )
                }
            } else {
                setLoadingStatus(false)
                setHasError()
            }
        }
    }

    private fun setLoadingStatus(status:Boolean){
        _uiState.update {
            it.copy(
                isLoading = status
            )
        }
    }

    private fun setHasError(){
        _uiState.update {
            it.copy(
                hasError = true
            )
        }
    }

}
data class HomeScreenUIState(
    val coinList: List<CoinMarketUI>? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)