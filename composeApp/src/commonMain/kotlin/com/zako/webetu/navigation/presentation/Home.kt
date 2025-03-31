package com.zako.webetu.navigation.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.zako.webetu.auth.login.presentation.LoginScreenRoot
import com.zako.webetu.home.presentation.HomeScreenRoot
import com.zako.webetu.navigation.model.AppGraph
import com.zako.webetu.navigation.model.Login
import com.zako.webetu.navigation.model.HomeScreen

fun NavGraphBuilder.homeGraph(
    navHostController: NavHostController,
) {
    composable<HomeScreen> {
        HomeScreenRoot()
    }
}