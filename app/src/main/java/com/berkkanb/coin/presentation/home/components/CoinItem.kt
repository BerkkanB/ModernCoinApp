package com.berkkanb.coin.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.berkkanb.coin.data.model.CoinMarketUI

@Composable
fun CoinItem(
    data: CoinMarketUI,
    onClickItem: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClickItem.invoke() }
    ) {
        AsyncImage(
            model = data.image,
            contentDescription = "Coin Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = data.name)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = data.symbol)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "$${data.currentPrice}")
    }
}