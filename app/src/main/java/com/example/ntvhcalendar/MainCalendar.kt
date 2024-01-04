@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.example.ntvhcalendar

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ntvhcalendar.screens.HomeScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun MainCalendar ()
{
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    var randomNumber by remember { mutableStateOf(5) } // Запоминаем переменную для рандома

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
        HomeScreen(randomNumber)

//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(5.dp)
//        ) {
//
//                //val randomNumber = random()
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                items(randomNumber) {
//                    ListItems()
//                }
//            }
//
//        }

        // Displays the indicator
        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
    

}

