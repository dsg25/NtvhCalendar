package com.example.nvhcalendar.calendars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nvhcalendar.models.ListItems
import com.example.nvhcalendar.screens.HomeScreen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random


@Composable
fun MainCalendar ()
{

    




    var refreshing by remember { mutableStateOf(false) }
    val randomNumber = Random.nextInt(1, 8)
    LaunchedEffect(refreshing) {
        if (refreshing) {

            delay(1000)
            refreshing = false
        }
    }


    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = false),
        onRefresh = { refreshing = true }) {

       // HomeScreen()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {

                //val randomNumber = random()
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(randomNumber) {
                    ListItems()
                }
            }

        }

    }
}

