package com.zako.webetu.auth.login.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreenRoot(modifier: Modifier = Modifier) {
    LoginScreen()
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Text("hello from login screen")
}