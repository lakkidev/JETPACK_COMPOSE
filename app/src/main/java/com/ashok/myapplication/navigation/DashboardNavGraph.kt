package com.ashok.myapplication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ashok.myapplication.screen.Screens
import com.ashok.myapplication.screen.FavScreen
import com.ashok.myapplication.screen.HomeScreen
import com.ashok.myapplication.screen.LyricScreen
import com.ashok.myapplication.screen.ProfileScreen

fun NavGraphBuilder.dashboardNavGraph(navController: NavController) {
    navigation(
        startDestination = Screens.Home.router,
        route = Screens.DashboardRoute.router
    ) {
        composable(Screens.Home.router) {
            HomeScreen(navController)
        }
        composable(Screens.Lyric.router) {
            LyricScreen(navController)
        }
        composable(Screens.Fav.router) {
            FavScreen(navController)
        }
        composable(Screens.Profile.router) {
            ProfileScreen(navController)
        }
    }
}
