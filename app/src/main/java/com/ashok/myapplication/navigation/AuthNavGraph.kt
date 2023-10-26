package com.ashok.myapplication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ashok.myapplication.screen.Screens
import com.ashok.myapplication.screen.ForgotPassword
import com.ashok.myapplication.screen.LoginScreen
import com.ashok.myapplication.screen.RegistrationScreen

fun NavGraphBuilder.authNavGraph(navController: NavController){
    navigation(startDestination = Screens.Login.router, route = Screens.AuthRoute.router){
        composable(Screens.Login.router) {
            LoginScreen(navController)
        }
        composable(Screens.Registration.router) {
            RegistrationScreen(navController)
        }
        composable(Screens.ForgotPassword.router) {
            ForgotPassword(navController)
        }
    }
}