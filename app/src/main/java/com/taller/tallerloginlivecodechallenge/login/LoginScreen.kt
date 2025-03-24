package com.ballyscorp.ballylive.ui.auth.login.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = LoginViewModel(),
    onSuccess: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setOnSuccess(onSuccess)
    }

    Scaffold{ contentPadding ->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = state.userName,
                label = {
                    Text("User Name")
                },
                onValueChange = viewModel::onUserNameChanged
            )

            TextField(
                value = state.password,
                label = {
                    Text("Password")
                },
                onValueChange = viewModel::onPasswordChanged,
                visualTransformation = PasswordVisualTransformation(),
            )

            Button(
                onClick = {
                    viewModel.onLogin(state.userName, state.password)
                }
            ) {
                Text(
                    text = "Login"
                )
            }

            if (state.shouldShowLoginFailedMessage) {
                Text(
                    text = "Username/Passwrod does not match"
                )
            }
        }
    }
}