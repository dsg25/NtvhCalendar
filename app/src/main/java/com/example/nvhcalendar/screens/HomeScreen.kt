package com.example.nvhcalendar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nvhcalendar.calendars.MainCalendar
import com.example.nvhcalendar.ui.theme.NvhCalendarTheme
import com.example.nvhcalendar.ui.theme.Teal700
import java.util.Locale


@Composable
fun HomeScreen() {
    // Установите русскую локаль
    val russianLocale = Locale("ru")
    Locale.setDefault(russianLocale)

    var date by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Top
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth(),
              //  .background(Color.Blue),
            Alignment.Center
        )
        {
            MainCalendar(russianLocale) { selectedDate ->
                date = selectedDate
            }

        }
        Divider( thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.background(Color.Cyan)
                //.border(2.dp, MaterialTheme.colorScheme.primaryContainer)
                .padding(5.dp)
        ) {

            Text(text = date, color = Teal700, fontWeight = Bold)

        }

        Divider( thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NvhCalendarTheme {
        HomeScreen()
    }
}