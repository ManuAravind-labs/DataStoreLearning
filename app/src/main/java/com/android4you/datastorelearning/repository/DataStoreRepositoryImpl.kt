package com.android4you.datastorelearning.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.android4you.datastorelearning.Constants
import com.android4you.datastorelearning.presentation.ProfileFormState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : IDataStoreRepository {
    override suspend fun updateProfileDetails(state: ProfileFormState) {
        dataStore.edit { settings ->
            settings[Constants.FIRST_NAME] = state.firstName
            settings[Constants.LAST_NAME] = state.lastName
            settings[Constants.PHONE_NUMBER] = state.phoneNumber
            settings[Constants.EMAIL] = state.email
        }
    }

    override fun getProfileDetails(): Flow<ProfileFormState> = dataStore.data.map {
        ProfileFormState(
            firstName = it[Constants.FIRST_NAME].orEmpty(),
            lastName = it[Constants.LAST_NAME].orEmpty(),
            email = it[Constants.EMAIL].orEmpty(),
            phoneNumber = it[Constants.PHONE_NUMBER].orEmpty(),
        )
    }
}
