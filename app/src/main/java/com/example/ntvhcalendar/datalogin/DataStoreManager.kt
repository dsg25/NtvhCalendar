package com.example.ntvhcalendar.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(private val context: Context) {

    suspend fun saveSettings(storageUserData: StorageUserData) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("local_name")] = storageUserData.localName
            pref[stringPreferencesKey("local_lastname")] = storageUserData.localLastName
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map StorageUserData(
            pref[stringPreferencesKey("local_name")] ?: "",
            pref[stringPreferencesKey("local_lastname")] ?: "",
            pref[intPreferencesKey("user_Id")] ?: 0

        )

    }
}