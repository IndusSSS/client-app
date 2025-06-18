package com.smartsecurity.clientapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smartsecurity.clientapp.ui.navigation.Dest

@Composable
fun LoginScreen(navController: NavController) {
    var user by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = user, onValueChange = { user = it }, label = { Text("Username") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = pwd, onValueChange = { pwd = it }, label = { Text("Password") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Dest.Devices.route) }) {
            Text("Login")
        }
    }
}
