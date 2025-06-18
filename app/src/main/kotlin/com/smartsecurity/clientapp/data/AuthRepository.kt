package com.smartsecurity.clientapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class LoginRequest(val username: String, val pwd: String)
@Serializable
data class LoginResponse(val jwt: String, val refresh: String)

class AuthRepository @Inject constructor(
    private val client: HttpClient,
    private val tokenStore: TokenStore
) {
    suspend fun login(user: String, pwd: String): Boolean {
        val resp: LoginResponse = client.post("/api/v1/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(user, pwd))
        }.body()
        tokenStore.saveJwt(resp.jwt)
        return true
    }
}
