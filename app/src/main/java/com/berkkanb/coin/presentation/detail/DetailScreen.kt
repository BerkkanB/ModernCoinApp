package com.berkkanb.coin.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jaikeerthick.composable_graphs.color.Gradient2
import com.jaikeerthick.composable_graphs.color.Gradient3
import com.jaikeerthick.composable_graphs.color.GraphAccent2
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.color.PointHighlight2
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility


@Composable
fun DetailScreen(
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val uiState by detailScreenViewModel.uiState.collectAsState()

    val lineGraphStyle = LineGraphStyle(
        visibility = LinearGraphVisibility(
            isHeaderVisible = true,
            isYAxisLabelVisible = true,
            isCrossHairVisible = true
        ),
        colors = LinearGraphColors(
            lineColor = GraphAccent2,
            pointColor = GraphAccent2,
            clickHighlightColor = PointHighlight2,
            fillGradient = Brush.verticalGradient(
                listOf(Gradient3, Gradient2)
            )
        )
    )

    with(uiState) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    coinDetail?.name?.let {
                        Text(
                            text = it,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    AsyncImage(
                        model = coinDetail?.image?.large,
                        contentDescription = "Coin Image",
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .padding(start = 10.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Favorite, contentDescription = "")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            uiState.priceData?.let {
                LineGraph(
                    xAxisData = it.time.map {
                        GraphData.String(it)
                    },
                    yAxisData = it.price,
                    style = lineGraphStyle
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            coinDetail?.priceChangePercentage24h?.let {
                Text(
                    text = "Price Change 24H",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it.toString()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            coinDetail?.description?.let {
                Text(
                    text = "Description",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it.en
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            coinDetail?.hashingAlgorithm?.let {
                Text(
                    text = "Hashing Algorithm",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it
                )
            }
        }
    }

}