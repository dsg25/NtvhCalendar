package com.example.ntvhcalendar.datacalendar

data class DataGroupEvent (
    val id: String,
    val name: String,
    val dateFrom: String,
    val dateTo: String,
    var color: String
){
    init {
        if (color.isEmpty()) {
            color = "#86b100"
        }
    }
}