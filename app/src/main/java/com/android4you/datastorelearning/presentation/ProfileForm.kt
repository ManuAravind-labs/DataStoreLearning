package com.android4you.datastorelearning.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileForm(viewModel: ProfileFormViewModel = hiltViewModel()) {
    val state = viewModel.profileFormState
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = state.firstName,
            onValueChange = {
                viewModel.onEvent(ProfileFormEvent.FirstNameChanged(it))
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "First Name")
            },
        )
        OutlinedTextField(
            value = state.lastName,
            onValueChange = {
                viewModel.onEvent(ProfileFormEvent.LastNameChanged(it))
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Last Name")
            },
        )
        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = {
                viewModel.onEvent(ProfileFormEvent.PhoneNumberChanged(it))
            },
            Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(text = "Phone Number")
            },

        )
        OutlinedTextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(ProfileFormEvent.EmailChanged(it))
            },
            Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(text = "E-Mail")
            },
        )

        OutlinedButton(onClick = { viewModel.onEvent(ProfileFormEvent.Submit) }) {
            Text(text = "Submit")
        }
    }
}
