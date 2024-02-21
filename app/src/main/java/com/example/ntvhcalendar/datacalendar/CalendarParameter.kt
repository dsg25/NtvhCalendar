package com.example.ntvhcalendar.datacalendar

data class CalendarParameter (
  //  val calendarCompany: String = "company_calendar",
    val calendarGroup: String = "type=group&ownerId=",
    val calendarGroupId: Int = 4, // Видеоинженеры = 4, Поездки = 8
    val calendarUser: String = "type=user&ownerId=",
    val calendarUserId: String = "1"
)