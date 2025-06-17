package com.smartsecurity.clientapp.api

import com.smartsecurity.clientapp.Config
import com.smartsecurity.clientapp.model.Sensor
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType

interface ApiService {
    @GET("api/v1/sensors")
    suspend fun getSensors(): List<Sensor>
}

object ApiClient {
    val service: ApiService by lazy {
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
        retrofit.create(ApiService::class.java)
    }
}

