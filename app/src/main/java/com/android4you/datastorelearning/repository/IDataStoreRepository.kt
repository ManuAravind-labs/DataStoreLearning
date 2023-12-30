package com.android4you.datastorelearning.repository

import com.android4you.datastorelearning.presentation.ProfileFormState
import kotlinx.coroutines.flow.Flow

interface IDataStoreRepository {
    suspend fun updateProfileDetails(state: ProfileFormState)

    fun getProfileDetails(): Flow<ProfileFormState>
}
