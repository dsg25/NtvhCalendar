package com.example.ntvhcalendar.datacalendar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItems(
    val name: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    object CompanyCalendar : BottomNavItems(
        name = "Расписание",
        route = "companyCalendar",
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
    )

    object UserCalendar : BottomNavItems(
        name = "Мой календарь",
        route = "userCalendar",
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
    )

    object UserTask : BottomNavItems(
        name = "Задачи",
        route = "userTask",
        selectedIcon = Icons.Filled.CheckCircle,
        unselectedIcon = Icons.Outlined.CheckCircle,
    )
}