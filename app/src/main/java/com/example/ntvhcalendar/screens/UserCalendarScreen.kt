package com.example.ntvhcalendar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ntvhcalendar.ui.theme.BaseBGSecondary

@Composable
fun UserCalendarScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseBGSecondary),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "USER CALENDAR", color = Color.White, fontSize = 30.sp)
    }
}

