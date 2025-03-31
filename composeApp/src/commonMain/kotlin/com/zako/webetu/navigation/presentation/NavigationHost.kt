package com.zako.webetu.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import arrow.core.rightIor
import com.zako.webetu.auth.user.UserController
import com.zako.webetu.auth.user.model.UserAuth
import com.zako.webetu.navigation.model.AppGraph
import com.zako.webetu.navigation.model.Login
import com.zako.webetu.navigation.model.MainScreen
import com.zako.webetu.notification.snackbare.SnackbarController
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun NavigationHost(
    rootNavController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        startDestination = Login ,
        navController = rootNavController
    ) {
        loginGraph(rootNavController)
        navigation<AppGraph>(
            startDestination = MainScreen,
        ){
            composable<MainScreen> {  }
        }

    }
}
