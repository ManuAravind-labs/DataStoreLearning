package com.android4you.datastorelearning

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val USER_PREFERENCE = "user_preference"
    val FIRST_NAME = stringPreferencesKey("first_name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val PHONE_NUMBER = stringPreferencesKey("phone_number")
    val EMAIL = stringPreferencesKey("email")
}
