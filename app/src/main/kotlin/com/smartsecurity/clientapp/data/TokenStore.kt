package com.smartsecurity.clientapp.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "token")
private val JWT_KEY = stringPreferencesKey("jwt")

@Singleton
class TokenStore @Inject constructor(@ApplicationContext context: Context) {
    private val store = context.dataStore

    suspend fun saveJwt(token: String) {
        store.edit { it[JWT_KEY] = token }
    }

    val jwtFlow: Flow<String> = store.data.map { it[JWT_KEY] ?: "" }
}
