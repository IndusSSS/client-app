package com.smartsecurity.clientapp.di

import com.smartsecurity.clientapp.data.AuthInterceptor
import com.smartsecurity.clientapp.data.TokenStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthInterceptor(store: TokenStore): AuthInterceptor = AuthInterceptor(store)

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: AuthInterceptor): HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) { json() }
        install(interceptor.plugin)
    }
}
