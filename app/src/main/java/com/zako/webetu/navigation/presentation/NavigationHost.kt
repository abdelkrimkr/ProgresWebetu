package com.zako.webetu.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zako.webetu.navigation.model.Login

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier ,
    rootNavController: NavHostController
) {

    NavHost(
        modifier = modifier ,
        startDestination = Login ,
        navController = rootNavController
    ){
        loginGraph(rootNavController)
    }
}