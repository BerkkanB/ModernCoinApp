package com.berkkanb.coin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.berkkanb.coin.presentation.auth.login.LoginScreen
import com.berkkanb.coin.presentation.auth.signup.SignUpScreen
import com.berkkanb.coin.presentation.detail.DetailScreen
import com.berkkanb.coin.presentation.home.HomeScreen
import com.google.firebase.auth.FirebaseUser

@Composable
fun CoinNavHost(
    firebaseUser: FirebaseUser?
) {
    val navController = rememberNavController()

    val startDestination =
        if (firebaseUser != null) CoinNavGraph.HOME_GRAPH else CoinNavGraph.LOGIN_GRAPH

    NavHost(navController = navController, startDestination = startDestination) {
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
        composable(
            CoinNavGraph.LOGIN_GRAPH
        ) {
            LoginScreen(
                navigateToHomeScreen = { navController.navigate(CoinNavGraph.HOME_GRAPH) },
                navigateToSignUpScreen = { navController.navigate(CoinNavGraph.SIGNUP_GRAPH) }
            )
        }
        composable(
            CoinNavGraph.SIGNUP_GRAPH
        ) {
            SignUpScreen(
                navigateToHomeScreen = { navController.navigate(CoinNavGraph.HOME_GRAPH) }
            )
        }
    }
}