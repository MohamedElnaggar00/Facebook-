package com.ycngmn.nobook.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "nobook_settings")

class SettingsDataStore(private val context: Context) {
    companion object {
        val DESKTOP_LAYOUT = booleanPreferencesKey("desktop_layout")
        val REVERT_DESKTOP = booleanPreferencesKey("revert_desktop")
        val IMMERSIVE_MODE = booleanPreferencesKey("immersive_mode")
        val AUTO_DESKTOP = booleanPreferencesKey("auto_desktop")
    }

    val desktopLayoutFlow: Flow<Boolean> = context.dataStore.data.map { it[DESKTOP_LAYOUT] ?: false }
    val isRevertDesktopFlow: Flow<Boolean> = context.dataStore.data.map { it[REVERT_DESKTOP] ?: false }
    val immersiveModeFlow: Flow<Boolean> = context.dataStore.data.map { it[IMMERSIVE_MODE] ?: false }
    val autoDesktopFlow: Flow<Boolean> = context.dataStore.data.map { it[AUTO_DESKTOP] ?: true }

    suspend fun setDesktopLayout(enabled: Boolean) {
        context.dataStore.edit { it[DESKTOP_LAYOUT] = enabled }
    }

    suspend fun setRevertDesktop(enabled: Boolean) {
        context.dataStore.edit { it[REVERT_DESKTOP] = enabled }
    }

    suspend fun setImmersiveMode(enabled: Boolean) {
        context.dataStore.edit { it[IMMERSIVE_MODE] = enabled }
    }

    suspend fun setAutoDesktop(enabled: Boolean) {
        context.dataStore.edit { it[AUTO_DESKTOP] = enabled }
    }
}
