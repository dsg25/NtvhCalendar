@file:OptIn(ExperimentalMaterialApi::class)

package com.example.ntvhcalendar.calendars

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.ntvhcalendar.screens.CompanyCalendarScreen
import com.example.ntvhcalendar.ui.theme.NtvhBlue
import com.example.ntvhcalendar.ui.theme.NtvhWhite
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CompanyCalendar ()
{
    val context = LocalContext.current
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    var randomNumber by remember { mutableIntStateOf(5) } // Запоминаем переменную для рандома

    // Выполнение операторов во время Refresh
    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)
        randomNumber = Random.nextInt(1, 12) // выбираем рандом для Листа
        refreshing = false
    }

    // Creates the pull refresh state
    val pullRefreshState = rememberPullRefreshState(refreshing, ::refresh)

    Box(
        // Attaches the pull refresh to the box
        modifier = Modifier
        .pullRefresh(pullRefreshState)

    ) {

        CompanyCalendarScreen(randomNumber)

        // Displays the indicator
        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
    

}


