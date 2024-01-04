package com.example.ntvhcalendar.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.ntvhcalendar.MainActivity
import com.example.ntvhcalendar.data.Credentials
import com.example.ntvhcalendar.data.DataStoreManager
import com.example.ntvhcalendar.data.LoadingDataUser
import com.example.ntvhcalendar.data.StorageUserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun checkCredentials(
    creds: Credentials, context: Context,
    dataStoreManager: DataStoreManager,
    storageNameState: MutableState<String>,
    storageLastNameState: MutableState<String>,
    checkUserCredentials: MutableState<Boolean>,
    usersList: MutableState<List<LoadingDataUser>>,
    coroutine: CoroutineScope

): Boolean {

    /*  Вычленяем совпадение с введенными данными и в filteredUsersList заносим это совпадение */
    val filteredUsersList = usersList.value.filter {
        it.name.contains(creds.loginName, ignoreCase = true) &&
                it.lastName.contains(creds.loginLastName, ignoreCase = true)
    }
    /* Из filteredUsersList выделяем три переменных name lastname userid*/
    val firstItem = filteredUsersList.firstOrNull()
    val (name, lastName, userid) = firstItem?.let { Triple(it.name, it.lastName, it.userId) }
        ?: (Triple("DefaultName", "DefaultLastName", 0))

      /*  Проверка на совпадение введеных данных и полученных с сервера */
    if (creds.isNotEmpty() &&
        creds.loginName.equals(name, ignoreCase = true) &&
        creds.loginLastName.equals(lastName, ignoreCase = true)
    ) {
        // Добавить запись успешного пользователя в StorageUserData

        coroutine.launch {
            dataStoreManager.saveSettings(
                StorageUserData(
                    name,
                    lastName,
                    userid
                )
            )
        }

        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()

        Log.d("Mylog", storageNameState.value)
        Log.d("Mylog", storageLastNameState.value)
        Log.d("Mylog", userid.toString())
    } else {
        checkUserCredentials.value = false
            }
    return checkUserCredentials.value
}
