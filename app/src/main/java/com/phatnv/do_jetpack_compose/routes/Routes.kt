package com.phatnv.do_jetpack_compose.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phatnv.do_jetpack_compose.screens.home.HomeScreen
import com.phatnv.do_jetpack_compose.screens.login.LoginScreen
import com.phatnv.do_jetpack_compose.screens.product.ProductScreen

enum class AppRoutes {
    LOGIN, HOME, PRODUCT
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = AppRoutes.LOGIN.name) {
        composable(AppRoutes.LOGIN.name) {
            LoginScreen(navController)
        }
        composable(AppRoutes.HOME.name) {
            HomeScreen(navController)
        }
        composable(AppRoutes.PRODUCT.name) {
            ProductScreen(navController)
        }
    }
}