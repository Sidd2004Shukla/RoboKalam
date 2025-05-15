package com.example.robokalamassignment.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {
    
    companion object {
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_PASSWORD = stringPreferencesKey("user_password")
    }

    val userEmail: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_EMAIL]
    }

    val userPassword: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_PASSWORD]
    }

    suspend fun saveUserCredentials(email: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
            preferences[USER_PASSWORD] = password
        }
    }

    suspend fun clearUserCredentials() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_EMAIL)
            preferences.remove(USER_PASSWORD)
        }
    }
} 