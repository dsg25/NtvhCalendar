package com.example.ntvhcalendar.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ntvhcalendar.calendars.BottomNavGraph
import com.example.ntvhcalendar.datacalendar.BottomNavItems
import com.example.ntvhcalendar.ui.theme.BaseBGSecondary
import com.example.ntvhcalendar.ui.theme.NtvhLightBlue
import com.example.ntvhcalendar.ui.theme.NtvhWhite


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },

        ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavItems.CompanyCalendar,
        BottomNavItems.UserCalendar,
        BottomNavItems.UserTask
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = BaseBGSecondary) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomNavItems,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        },
        label = {
            Text(text = screen.name, fontSize = 11.sp)
        },
        alwaysShowLabel = false,
        icon = {
            Icon(
                imageVector = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    screen.selectedIcon
                } else screen.unselectedIcon,
                contentDescription = screen.name
            )
        },

        selectedContentColor = NtvhLightBlue,
        unselectedContentColor = NtvhWhite
    )

}
