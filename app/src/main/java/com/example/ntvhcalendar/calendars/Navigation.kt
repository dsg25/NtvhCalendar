package com.example.ntvhcalendar.calendars

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ntvhcalendar.datacalendar.BottomNavItems
import com.example.ntvhcalendar.screens.TaskScreen


@Composable
fun BottomNavgraph(navController : NavHostController) {
   NavHost(navController = navController, startDestination = BottomNavItems.CompanyCalendar.route ){
      composable(route = BottomNavItems.CompanyCalendar.route){
      CompanyCalendar()
       }
       composable(route = BottomNavItems.UserCalendar.route){
       UserCalendar()
       }
       composable(route = BottomNavItems.UserTask.route){
           TaskScreen()
       }
   }
}
