package com.smartsecurity.clientapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smartsecurity.clientapp.api.ApiClient
import com.smartsecurity.clientapp.model.Sensor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorScreen() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var sensors by remember { mutableStateOf<List<Sensor>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }

    fun loadSensors() {
        coroutineScope.launch {
            isLoading = true
            try {
                sensors = ApiClient.service.getSensors()
            } catch (e: Exception) {
                snackbarHostState.showSnackbar("Network error")
            }
            isLoading = false
        }
    }

    LaunchedEffect(Unit) { loadSensors() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Sensors") },
                actions = {
                    IconButton(onClick = { loadSensors() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        }
    ) { padding ->
        if (sensors.isEmpty() && !isLoading) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding), contentAlignment = Alignment.Center) {
                Text("No sensors found.")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(sensors) { sensor ->
                    SensorRow(sensor)
                }
            }
        }
    }
}

@Composable
private fun SensorRow(sensor: Sensor) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(sensor.name, modifier = Modifier.weight(1f))
        val (color, icon) = if (sensor.status == "online") {
            Color(0xFF4CAF50) to Icons.Default.Check
        } else {
            Color(0xFFF44336) to Icons.Default.Close
        }
        AssistChip(
            onClick = {},
            label = { Text(sensor.status) },
            leadingIcon = {
                Icon(icon, contentDescription = null)
            },
            colors = AssistChipDefaults.assistChipColors(containerColor = color)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SensorRowPreview() {
    SensorRow(Sensor("Door-1", "online", 3.3f))
}

