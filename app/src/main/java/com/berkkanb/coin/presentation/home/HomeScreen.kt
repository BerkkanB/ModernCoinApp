package com.berkkanb.coin.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkkanb.coin.presentation.home.components.CoinItem

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val uiState by homeScreenViewModel.uiState.collectAsState()

    LazyColumn(){
        uiState.coinList?.let { coinMarketData ->
            items(coinMarketData, key = {coin -> coin.id}){
                CoinItem(
                    data = it,
                    onClickItem = {navigateToDetail.invoke(it.id)}
                )
            }
        }
    }
}