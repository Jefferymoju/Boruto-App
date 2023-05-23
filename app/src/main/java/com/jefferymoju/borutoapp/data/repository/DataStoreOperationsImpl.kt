package com.jefferymoju.borutoapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.jefferymoju.borutoapp.domain.repository.DataStoreOperations
import com.jefferymoju.borutoapp.util.Constants.PREFERENCES_KEY
import com.jefferymoju.borutoapp.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
by preferencesDataStore(name = PREFERENCES_NAME) // this extension val will be use to access the dataStore

class DataStoreOperationsImpl(context: Context): DataStoreOperations {

    private object  PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY) // we need to tell the datastore library
        // under which name or under which key that boolean value will be saved
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
       dataStore.edit {preferences ->
           preferences[PreferencesKey.onBoardingKey] = completed // the key which will want to save a boolean value
       }
    }

    // we need to read the same value we saved to the key from the key
    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch {exception ->
                if (exception is IOException){
                    emit(emptyPreferences())
                }else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}