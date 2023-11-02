package com.muammarahlnn.urflix.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.muammarahlnn.urflix.core.datastore.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file UrflixPreferencesDataStore, 02/11/2023 18.03 by Muammar Ahlan Abimanyu
 */

val Context.urflixPreferences: DataStore<Preferences> by preferencesDataStore("garamQu")

class UrflixPreferencesDataStore @Inject constructor(
    private val preferences: DataStore<Preferences>
) {

    private object PreferencesKeys {

        val FULL_NAME = stringPreferencesKey("full_name")

        val EMAIL = stringPreferencesKey("email")
    }

    suspend fun saveUser(user: UserEntity) {
        preferences.edit { pref ->
            pref[PreferencesKeys.FULL_NAME] = user.fullName
            pref[PreferencesKeys.EMAIL] = user.email
        }
    }

    fun getUser(): Flow<UserEntity> = preferences.data.map { pref ->
        UserEntity(
            fullName = pref[PreferencesKeys.FULL_NAME] ?: "",
            email = pref[PreferencesKeys.EMAIL] ?: "",
        )
    }
}