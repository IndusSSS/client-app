package com.smartsecurity.clientapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.smartsecurity.clientapp.ui.navigation.Dest

@Composable
fun DeviceListScreen(navController: NavController) {
    val devices = listOf("Camera 1", "Door 1")
    Column(Modifier.fillMaxSize()) {
        LazyColumn {
            items(devices) { device ->
                Text(device, Modifier
                    .fillMaxSize())
            }
        }
    }
}
