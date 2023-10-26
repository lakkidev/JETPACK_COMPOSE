package com.ashok.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ashok.myapplication.navigation.bottomNavigation
import com.ashok.myapplication.navigation.dashboardNavGraph
import com.ashok.myapplication.navigation.drawerContent
import com.ashok.myapplication.navigation.topAppBar
import com.ashok.myapplication.screen.Screens
import com.ashok.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val currentRoute = currentDestination?.route ?: Screens.DashboardRoute.router
                val scope = rememberCoroutineScope()
                val items = listOf(
                    Screens.Home, Screens.Lyric, Screens.Fav, Screens.Profile
                )
                ModalNavigationDrawer(
                    drawerContent = {
                        drawerContent(
                            items = items,
                            currentDestination = currentDestination,
                            onClickDrawerItem = { item ->
                                scope.launch {
                                    navigationState.close()
                                }
                                navController.navigate(item.router) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    },
                    drawerState = navigationState
                ) {
                    MainScreen(navController, navigationState, currentRoute)
                }

            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController,
    navigationState: DrawerState,
    currentRoute: String
) {
    Scaffold(
        topBar = {
            topAppBar(
                navigationState = navigationState,
                currentRoute = currentRoute
            )
        },
        bottomBar = {
            bottomNavigation(navController)
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.DashboardRoute.router,
            modifier = Modifier.padding(paddingValues = it)
        ) {
            dashboardNavGraph(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        MainScreen(
            rememberNavController(),
            rememberDrawerState(initialValue = DrawerValue.Open),
            currentRoute = Screens.DashboardRoute.router
        )
    }
}