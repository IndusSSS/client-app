package com.smartsecurity.clientapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DeviceDetailScreen(navController: NavController, id: String) {
    Column {
        Text("Detail for $id")
    }
}
