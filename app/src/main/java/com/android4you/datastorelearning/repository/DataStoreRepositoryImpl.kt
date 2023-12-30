package com.android4you.datastorelearning.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.android4you.datastorelearning.Constants
import com.android4you.datastorelearning.presentation.ProfileFormState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : IDataStoreRepository {
    suspend fun updateProfileDetails(state: ProfileFormState) {
        putPreference(Constants.FIRST_NAME, state.firstName)
        putPreference(Constants.LAST_NAME, state.lastName)
        putPreference(Constants.PHONE_NUMBER, state.phoneNumber)
        putPreference(Constants.EMAIL, state.email)
    }

    fun getProfileDetails(): Flow<ProfileFormState> = dataStore.data.map {
        ProfileFormState(
            firstName = getFirstPreference(Constants.FIRST_NAME, ""),
            lastName = getFirstPreference(Constants.LAST_NAME, ""),
            email = getFirstPreference(Constants.EMAIL, ""),
            phoneNumber = getFirstPreference(Constants.PHONE_NUMBER, ""),
        )
    }

    /* This returns us a flow of data from DataStore.
       Basically as soon we update the value in Datastore,
       the values returned by it also changes. */
    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T):
        Flow<T> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val result = preferences[key] ?: defaultValue
        result
    }

    /* This returns the last saved value of the key. If we change the value,
        it wont effect the values produced by this function */
    override suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T):
        T = dataStore.data.first()[key] ?: defaultValue

    // This Sets the value based on the value passed in value parameter.
    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    // This Function removes the Key Value pair from the datastore, hereby removing it completely.
    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    // This function clears the entire Preference Datastore.
    override suspend fun <T> clearAllPreference() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
