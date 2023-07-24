package com.berkkanb.coin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.berkkanb.coin.presentation.detail.DetailScreen
import com.berkkanb.coin.presentation.home.HomeScreen

@Composable
fun CoinNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CoinNavGraph.HOME_GRAPH) {
        composable(CoinNavGraph.HOME_GRAPH) {
            HomeScreen(
                navigateToDetail = { navController.navigate("${CoinNavGraph.DETAIL_GRAPH}/$it") }
            )
        }
        composable(
            "${CoinNavGraph.DETAIL_GRAPH}/{coinId}",
            arguments = listOf(navArgument("coinId") { type = NavType.StringType })
        ) {
            DetailScreen()
        }
    }
}