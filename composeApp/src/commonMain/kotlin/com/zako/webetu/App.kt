package com.zako.webetu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zako.webetu.navigation.presentation.NavigationHost
import com.zako.webetu.notification.snackbare.SnackbarController
import com.zako.webetu.theme.WebetuTheme
import com.zako.webetu.utils.ObserveAsEvents
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val rootNavController = rememberNavController()
    WebetuTheme(
        darkTheme = false,
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        ObserveAsEvents(
            flow = SnackbarController.events,
            snackbarHostState
        ) { event ->
            scope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()

                val result = snackbarHostState.showSnackbar(
                    message = event.message,
                    actionLabel = event.action?.name,
                    duration = event.duration
                )

                if (result == SnackbarResult.ActionPerformed) {
                    event.action?.action?.invoke()
                }
            }
        }
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            },
            modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavigationHost(
                modifier = Modifier.padding(innerPadding),
                rootNavController = rootNavController
            )
        }
    }
}