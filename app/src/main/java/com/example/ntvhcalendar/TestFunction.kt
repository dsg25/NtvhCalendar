package com.example.ntvhcalendar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun TestFunction ()
{


  //  val context = LocalContext.current
    val userIdState = remember { mutableStateOf(0) }
  //  val dataStoreManager = DataStoreManager(LocalContext.current)

//    LaunchedEffect(key1 = true) {
//        dataStoreManager.loadSettings().collect { settings ->
//            userIdState.value = settings.userId
//        }
//    }

   // val userIdName = userIdState.value

    //val calendarMethod = CalendarMethod()
    //val calendarParameter = CalendarParameter()//calendarGroupId = userIdName)

   // val nameCalendarParameter = calendarParameter.calendarGroup + calendarParameter.calendarGroupId

  //  val url = requestApi(context = LocalContext.current, calendarMethod.calendarEventGet, nameCalendarParameter)

    Text(text = "234JKKJHKJHKJ")//url)

}