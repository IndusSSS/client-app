package com.smartsecurity.clientapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smartsecurity.clientapp.ui.screens.DeviceDetailScreen
import com.smartsecurity.clientapp.ui.screens.DeviceListScreen
import com.smartsecurity.clientapp.ui.screens.LoginScreen

sealed class Dest(val route: String) {
    data object Login : Dest("login")
    data object Devices : Dest("devices")
    data object Detail : Dest("detail/{id}") {
        fun create(id: String) = "detail/$id"
    }
}

@Composable
fun SmartNav(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = Dest.Login.route) {
        composable(Dest.Login.route) { LoginScreen(navController) }
        composable(Dest.Devices.route) { DeviceListScreen(navController) }
        composable(Dest.Detail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            DeviceDetailScreen(navController, id)
        }
    }
}
