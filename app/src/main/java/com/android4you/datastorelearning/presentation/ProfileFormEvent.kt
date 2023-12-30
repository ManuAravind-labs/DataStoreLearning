package com.android4you.datastorelearning.presentation

sealed class ProfileFormEvent {
    data class FirstNameChanged(val firstName: String) : ProfileFormEvent()
    data class LastNameChanged(val lastName: String) : ProfileFormEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : ProfileFormEvent()
    data class EmailChanged(val email: String) : ProfileFormEvent()
    object Submit : ProfileFormEvent()
}
