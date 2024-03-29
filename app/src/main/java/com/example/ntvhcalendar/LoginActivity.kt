package com.example.ntvhcalendar

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ntvhcalendar.datamain.ApiKey
import com.example.ntvhcalendar.data.LoadingDataUser
import com.example.ntvhcalendar.screens.LoginScreen
import com.example.ntvhcalendar.ui.theme.BaseBGSecondary
import com.example.ntvhcalendar.ui.theme.NtvhCalendarTheme
import kotlinx.coroutines.delay
import org.json.JSONObject

val API_KEY = ApiKey()

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = getColor(R.color.BaseBGSecondary)
            window.navigationBarColor = getColor(R.color.BaseBGSecondary)
            NtvhCalendarTheme(darkTheme = true) {

                /* Скрываем Нижнюю системную панель */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val windowInsetsController = window.insetsController
                    windowInsetsController?.hide(WindowInsets.Type.navigationBars())
                } else {
                    // Для более старых версий
                    window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                }
                /* END */

                val context = LocalContext.current
                val usersList = remember { mutableStateOf(listOf<LoadingDataUser>()) }

                var isLoading by remember { mutableStateOf(true) }

                LaunchedEffect(true) {
                    requestUserList(context, usersList)
                    delay(2000)
                    isLoading = false
                }
                if (isLoading) {
                    LoadingScreen()
                } else {
                    LoginScreen(usersList)
                }
            }
        }
    }
}

// Функцию вынести в отдельный файл в Utils
private fun requestUserList(
    context: Context, usersList: MutableState<List<LoadingDataUser>>
) {
    val url = "https://narodnoetv.bitrix24.ru/rest/6/${API_KEY.ApiKey}/user.get/?ID:"

    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(Request.Method.GET, url, { response ->
        val list = getData(response)
        usersList.value = list
    }, {
        Toast.makeText(context, "Ошибка связи: $it", Toast.LENGTH_LONG).show()
    })
    queue.add(sRequest)
}

private fun getData(responce: String): List<LoadingDataUser> {
    if (responce.isEmpty()) return listOf()
    val list = ArrayList<LoadingDataUser>()
    val resultArray = JSONObject(responce).getJSONArray("result")

    for (i in 0 until resultArray.length()) {
        val item = resultArray[i] as JSONObject
        val name = item.getString("NAME")
        val lastname = item.getString("LAST_NAME")
        val userId = item.getString("ID").toInt()

        list.add(LoadingDataUser(name, lastname, userId))

    }
    return list
}

@Composable // Функцию вынести в отдельный файл
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseBGSecondary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.narodnoe_full_logo),
            contentDescription = "Logo"
        )
        CircularProgressIndicator(color = Color.White)
    }
}