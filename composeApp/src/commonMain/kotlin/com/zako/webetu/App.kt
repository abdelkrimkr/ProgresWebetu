package com.zako.webetu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zako.webetu.navigation.presentation.NavigationHost
import com.zako.webetu.theme.WebetuTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
        val rootNavController = rememberNavController()
        WebetuTheme(
            darkTheme = false ,
        ) {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                NavigationHost(
                    modifier = Modifier.padding(innerPadding),
                    rootNavController = rootNavController
                )
            }
        }
}