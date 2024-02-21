package com.example.ntvhcalendar.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ntvhcalendar.API_KEY
import com.example.ntvhcalendar.data.LoadingDataUser
import com.example.ntvhcalendar.datacalendar.DataGroupEvent
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun requestGroupEvent (
    context: Context, calendarMethod: String, CalendarParameter: String,
    groupEventList: MutableState<List<DataGroupEvent>>
):String {


    val url = "https://narodnoetv.bitrix24.ru/rest/6/${API_KEY.ApiKey}/${calendarMethod}/?${CalendarParameter}"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(Request.Method.GET, url, { response ->
        val list = getDataGroupEvent(response)
        groupEventList.value = list
    }, {
        Toast.makeText(context, "Ошибка связи: $it", Toast.LENGTH_LONG).show()
    })
    queue.add(sRequest)
return url
}

private fun getDataGroupEvent(responce: String): List<DataGroupEvent> {
    if (responce.isEmpty()) return listOf()
    val list = ArrayList<DataGroupEvent>()
    val resultArray = JSONObject(responce).getJSONArray("result")

    for (i in 0 until resultArray.length()) {
        val item = resultArray[i] as JSONObject
        val idEvent = item.getString("ID")
        val name = item.getString("NAME")

        val dateFrom = LocalDateTime.parse(item.getString("DATE_FROM"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        // Форматируем время в нужный формат
        val timeFrom = dateFrom.format(DateTimeFormatter.ofPattern("HH:mm"))

        val dateTo = LocalDateTime.parse(item.getString("DATE_TO"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        // Форматируем время в нужный формат
        val timeTo = dateTo.format(DateTimeFormatter.ofPattern("HH:mm"))

        val color = item.getString("COLOR")

        list.add(DataGroupEvent(idEvent, name, timeFrom, timeTo, color))

       // Log.d("MyLog", DataGroupEvent(name, timeFrom,dateTo, color).name)
       //Log.d("MyLog",DataGroupEvent(name, timeFrom,dateTo, color).color)
    }
    return list
}