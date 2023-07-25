package com.berkkanb.coin.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthScreen(
    authScreenViewModel: AuthScreenViewModel = hiltViewModel(),
    navigateToHomeScreen:()->Unit
) {

    val uiState by authScreenViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.firebaseUser){
        if (uiState.firebaseUser != null){
            navigateToHomeScreen.invoke()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = uiState.email,
            onValueChange = { authScreenViewModel.setEmail(it) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value =uiState.password,
            onValueChange = { authScreenViewModel.setPassword(it) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(
                onClick = { authScreenViewModel.signInUser() }
            ) {
                Text("Sign In")
            }
            Button(
                onClick = { authScreenViewModel.signUpUser() }
            ) {
                Text("Sign Up")
            }
            if (uiState.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}