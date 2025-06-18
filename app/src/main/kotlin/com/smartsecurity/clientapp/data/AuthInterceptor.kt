package com.smartsecurity.clientapp.data

import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class AuthInterceptor(private val tokenStore: TokenStore) {
    val plugin = createClientPlugin("AuthInterceptor") {
        onRequest { request, _ ->
            val token = runBlocking { tokenStore.jwtFlow.first() }
            if (token.isNotBlank()) {
                request.headers.append(HttpHeaders.Authorization, "Bearer $token")
            }
        }
    }
}
