@file:OptIn(ExperimentalMaterialApi::class)

package com.example.ntvhcalendar.calendars

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ntvhcalendar.screens.groupCalendarScreen
import com.example.ntvhcalendar.ui.theme.NtvhLightBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupCalendar() {
    val context = LocalContext.current
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

     // Toast.makeText(context, "sdfsd", Toast.LENGTH_SHORT).show()

    // Выполнение операторов во время Refresh
    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)

        refreshing = false
    }

    // Creates the pull refresh state
    val pullRefreshState = rememberPullRefreshState(refreshing, ::refresh)
    Scaffold(
        content = {
            Box(
                // Attaches the pull refresh to the box
                modifier = Modifier
                    .pullRefresh(pullRefreshState)

            ) {

                groupCalendarScreen(context)

                // Отображение индикатора обновления
                PullRefreshIndicator(
                    refreshing = refreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "Добавляем съемку", Toast.LENGTH_LONG).show()
                          /*Добавляем событие в расписание*/ },
                modifier = Modifier
                    .zIndex(20f)
                    .padding(start = 30.dp, bottom = 50.dp),

                content = { Icon(Icons.Default.Add, "Add") },
                contentColor = Color.Black,
                containerColor = NtvhLightBlue,
                shape = CircleShape
            )
        },
        //isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End,
    )

}



