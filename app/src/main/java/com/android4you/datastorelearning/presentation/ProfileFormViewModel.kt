package com.android4you.datastorelearning.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.datastorelearning.Constants
import com.android4you.datastorelearning.repository.DataStoreRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFormViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepositoryImpl,
) : ViewModel() {
    var profileFormState by mutableStateOf(ProfileFormState())
        private set

    init {
        viewModelScope.launch {
            dataStoreRepository.getProfileDetails().collect { newState ->
                profileFormState = newState
            }
        }
    }

    fun onEvent(event: ProfileFormEvent) {
        when (event) {
            is ProfileFormEvent.EmailChanged -> {
                profileFormState = profileFormState.copy(email = event.email)
            }

            is ProfileFormEvent.FirstNameChanged -> {
                profileFormState = profileFormState.copy(firstName = event.firstName)
            }

            is ProfileFormEvent.LastNameChanged -> {
                profileFormState = profileFormState.copy(lastName = event.lastName)
            }

            is ProfileFormEvent.PhoneNumberChanged -> {
                profileFormState = profileFormState.copy(phoneNumber = event.phoneNumber)
            }

            ProfileFormEvent.Submit -> {
                saveData()
            }
        }
    }

    private fun saveData() {
        viewModelScope.launch {
            dataStoreRepository.updateProfileDetails(profileFormState)
        }
    }

    private fun test() {
        viewModelScope.launch {
            dataStoreRepository.getFirstPreference(Constants.FIRST_NAME, "")
        }
    }
}
