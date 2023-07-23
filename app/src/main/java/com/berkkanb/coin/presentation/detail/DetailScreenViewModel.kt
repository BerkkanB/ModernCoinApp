package com.berkkanb.coin.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkkanb.coin.data.model.CoinDataUI
import com.berkkanb.coin.data.model.CoinMarketUI
import com.berkkanb.coin.domain.CoinDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val coinDataRepository: CoinDataRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val coinId: String = checkNotNull(savedStateHandle["coinId"])

    private val _uiState = MutableStateFlow(DetailScreenUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getCoinDetail()
    }

    private fun getCoinDetail(){
        viewModelScope.launch {
            setLoadingStatus(true)
            val response = coinDataRepository.getCoinDetail(coinId)
            if (response.isSuccessful){
                _uiState.update {
                    it.copy(
                        coinDetail = response.body(),
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

data class DetailScreenUIState(
    val coinDetail: CoinDataUI? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)