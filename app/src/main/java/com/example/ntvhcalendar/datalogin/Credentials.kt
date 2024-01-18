package com.example.ntvhcalendar.data

/* Данные для проверки учетных данных  */

data class Credentials(var loginName: String = "", var loginLastName: String = "") {
    fun isNotEmpty(): Boolean {
        return loginName.isNotEmpty() && loginLastName.isNotEmpty()
    }
}