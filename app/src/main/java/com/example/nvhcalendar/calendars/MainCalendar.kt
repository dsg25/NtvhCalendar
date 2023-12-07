package com.example.nvhcalendar.calendars

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.nvhcalendar.ui.theme.Gray60
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun MainCalendar (locale: Locale, onDateSelected: (String) -> Unit )
{
    val currentdata = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("d.MM.yyyy")
    val formattedDate = currentdata.format(formatter)



    AndroidView(factory = { context ->



        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.createConfigurationContext(configuration)

        onDateSelected(formattedDate) // Заносим в переменную текущую дату

        CalendarView(context) },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Gray60)
            .height(310.dp),
        update = {
            it.setOnDateChangeListener { _, year, month, day ->
                val formattedDateListener = "$day.${month + 1}.$year"
                onDateSelected(formattedDateListener) // Заносим в переменную выбранную дату
            }
        }
    )

   }