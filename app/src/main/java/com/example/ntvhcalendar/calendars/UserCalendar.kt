package com.example.ntvhcalendar.calendars

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ntvhcalendar.screens.CompanyCalendarScreen
import com.example.ntvhcalendar.screens.UserCalendarScreen
import com.example.ntvhcalendar.ui.theme.NtvhGreen
import com.example.ntvhcalendar.ui.theme.NtvhLightBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserCalendar() {
    Scaffold(
        content = {
            Box(
                // Attaches the pull refresh to the box
                modifier = Modifier


            ) {
                UserCalendarScreen()
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*Добавляем событие в мой календарь*/ },
                modifier = Modifier
                    .zIndex(20f)
                    .padding(start = 30.dp, bottom = 50.dp),

                content = { Icon(Icons.Default.Add, "Add") },
                contentColor = Color.Black,
                containerColor = NtvhGreen,
                shape = CircleShape
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    )
}

