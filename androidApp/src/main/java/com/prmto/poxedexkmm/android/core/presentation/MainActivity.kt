package com.prmto.poxedexkmm.android.core.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.prmto.poxedexkmm.android.core.navigation.PoxedexNavigation
import com.prmto.poxedexkmm.android.core.navigation.Screens
import com.prmto.poxedexkmm.android.core.navigation.rememberBottomNavigationItems
import com.prmto.poxedexkmm.core.presentation.Colors

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            MyApplicationTheme {
                WindowCompat.setDecorFitsSystemWindows(window, true)

                val bottomNavigationItems = rememberBottomNavigationItems()
                val currentBackStackEntry = navController.currentBackStackEntryAsState()

                Scaffold(
                    bottomBar = {
                        ShowBottomNavigation(
                            currentBackStackEntry = currentBackStackEntry.value
                        ) {
                            BottomNavigation(
                                backgroundColor = Color.White
                            ) {
                                bottomNavigationItems.value.forEach { navigationItem ->
                                    BottomNavigationItem(
                                        alwaysShowLabel = false,
                                        selectedContentColor = Color(Colors.EgyptianBlue),
                                        selected = navigationItem.selected,
                                        onClick = {
                                            navController.navigate(navigationItem.route) {
                                                launchSingleTop = true
                                            }
                                            bottomNavigationItems.value =
                                                bottomNavigationItems.value.map {
                                                    it.copy(selected = it == navigationItem)
                                                }
                                        },
                                        label = {
                                            Text(text = stringResource(id = navigationItem.labelTextRes))
                                        },
                                        icon = {
                                            val painterResID = if (navigationItem.selected) {
                                                navigationItem.selectedIconDrawable
                                            } else {
                                                navigationItem.unSelectedIconDrawable
                                            }
                                            Icon(
                                                painter = painterResource(id = painterResID),
                                                contentDescription = null
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    },
                    content = {
                        PoxedexNavigation(navController = navController)
                    },
                )
            }
        }
    }

    @Composable
    private fun ShowBottomNavigation(
        currentBackStackEntry: NavBackStackEntry?,
        content: @Composable () -> Unit
    ) {
        val currentRoute = currentBackStackEntry?.destination?.route
        val hideBottomBarRoutes = listOf(
            Screens.SplashScreen.route,
            Screens.OnBoardingScreen.route
        )
        if (currentRoute in hideBottomBarRoutes) {
            return
        }
        content()
    }
}