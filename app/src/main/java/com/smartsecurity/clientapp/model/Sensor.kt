package com.smartsecurity.clientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Sensor(
    val name: String,
    val status: String,
    val voltage: Float
)

