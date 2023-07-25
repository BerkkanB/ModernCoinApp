package com.berkkanb.coin.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkkanb.coin.presentation.home.components.CoinItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val uiState by homeScreenViewModel.uiState.collectAsState()

    var isActive by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .zIndex(1f)
                .fillMaxWidth()
        ) {
            SearchBar(
                modifier = Modifier.align(Alignment.TopCenter),
                query = uiState.queryText,
                onQueryChange = {
                    homeScreenViewModel.setQueryText(it)
                },
                onSearch = {
                    isActive = false
                },
                active = isActive,
                onActiveChange = {
                    isActive = it
                },
                placeholder = { Text("Hinted search text") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    uiState.filteredCoinList?.let {
                        items(it) { coin ->
                            CoinItem(
                                data = coin,
                                onClickItem = { navigateToDetail.invoke(coin.id) })
                        }
                    }
                }
            }
        }

        LazyColumn() {
            uiState.coinList?.let { coinMarketData ->
                items(coinMarketData, key = { coin -> coin.id }) {
                    CoinItem(
                        data = it,
                        onClickItem = { navigateToDetail.invoke(it.id) }
                    )
                }
            }
        }
    }
}