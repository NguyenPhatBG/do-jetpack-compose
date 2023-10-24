package com.phatnv.do_jetpack_compose.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.phatnv.do_jetpack_compose.routes.AppRoutes

@Composable
fun LoginScreen(navController: NavHostController) {
    Text(text = "Login screen", modifier = Modifier.clickable {
        navController.navigate(AppRoutes.HOME.name)
    })
}