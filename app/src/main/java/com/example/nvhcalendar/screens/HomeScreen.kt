package com.example.nvhcalendar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.example.nvhcalendar.R
import com.example.nvhcalendar.calendars.RussianMonth
import com.example.nvhcalendar.ui.theme.BaseBGPrimary
import com.example.nvhcalendar.ui.theme.NvhCalendarTheme


@Composable
fun HomeScreen()
{

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed

    var selectedDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) } // Выбранная дата
    // val daysOfWeek = daysOfWeek()
    // val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseBGPrimary),
    ) {

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .align(Alignment.Start),


            ){

            val state = rememberCalendarState(
                startMonth = startMonth,
                endMonth = endMonth,
                firstVisibleMonth = currentMonth,
                firstDayOfWeek = firstDayOfWeekFromLocale(), //firstDayOfWeek,

            )

            //DaysOfWeekTitle(daysOfWeek = daysOfWeek)
            HorizontalCalendar(
                state = state,
                dayContent = { day ->
                    Day(day, isSelected = selectedDate == day.date) { _ ->
                        selectedDate = if (selectedDate == day.date) null else day.date
                    }
                },
                monthHeader = { month -> MonthHeader(month) },
                contentPadding = PaddingValues(4.dp),
                monthContainer = { _, container ->
                    val configuration = LocalConfiguration.current
                    val screenWidth = configuration.screenWidthDp.dp
                    Box(
                        modifier = Modifier
                            .width(screenWidth * 1f)
                            .padding(8.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
//                            .border(
//                                color = Color.Black,
//                                width = 1.dp,
//                                shape = RoundedCornerShape(8.dp)
//                            )


                    )
                    {
                        container()
                    }
                },
                monthBody = { _, content ->
                    Box(
                        modifier = Modifier.background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFB2EBF2),
                                    Color(0xFFB2B8F2)
                                )

                            )
                        )
                    ) {
                        content() // Render the provided content!
                    }
                },

                )
        }


        Text( text = LocalDate.now().toString())
        Text( text = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
            .replaceFirstChar { it.uppercase(Locale.getDefault()) })



        Text(text = selectedDate.toString())
// ************ Вывод дня недели *************
        selectedDate?.let {
            Text( text = it.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()) )
        }
//     ********************





    }
}


@Composable
private fun MonthHeader(calendarMonth: CalendarMonth) {
    val daysOfWeek = calendarMonth.weekDays.first().map { it.date.dayOfWeek }
    val russianMonth = RussianMonth.entries[calendarMonth.yearMonth.monthValue - 1] // -1, так как месяцы в Java начинаются с 1

    Column(
        modifier = Modifier
            .wrapContentHeight()
          //  .background(color = colorResource(R.color.purple_500))
            .padding(top = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp),
            text = "${russianMonth.displayName.uppercase()} ${calendarMonth.yearMonth.year}",

            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    text = dayOfWeek.displayText(uppercase = false),
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Divider(color = Color.Black)
    }
}



@Composable
private fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {

    Box(
        Modifier
            .fillMaxWidth()
            .height(30.dp)
            .clip(CircleShape)
            .background(color = if (isSelected) Color.Green else Color.Transparent)
            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                onClick = {
                    if (!isSelected) {
                        onClick(day)
                    }
                }
            ),

        contentAlignment = Alignment.CenterStart
    ) {

        if (day.position == DayPosition.MonthDate) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = day.date.dayOfMonth.toString(),
                color = Color.Black,
                fontSize = 14.sp,
            )
        }



    }
}

@Composable
fun DayOfWeek.displayText(uppercase: Boolean = false): String {
    val currentLocale = LocalConfiguration.current.locales[0]
    return getDisplayName(TextStyle.SHORT, currentLocale).let { value ->
        if (uppercase) value.uppercase(currentLocale) else value }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NvhCalendarTheme (darkTheme = true){
        HomeScreen()
    }
}



// *********** ВЫВОДИМ СПИСОК LISTITEM ***************
//Box(
//modifier = Modifier
//.fillMaxSize()
//.padding(5.dp)
//) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(15){
//            ListItems()
//        }
//    }
//
//}