package com.example.ntvhcalendar

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ntvhcalendar.screens.NavigationScreen
import com.example.ntvhcalendar.ui.theme.NtvhCalendarTheme
import com.example.ntvhcalendar.ui.theme.NtvhLightBlue

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            window.statusBarColor = getColor(R.color.BaseBGPrimary)
            window.navigationBarColor = getColor(R.color.BaseBGPrimary)
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

//            Scaffold (
//floatingActionButton = {
//    FloatingActionButton(
//        onClick = { /*TODO*/ },
//        modifier = Modifier
//            .zIndex(20f)
//            .padding(start = 30.dp, bottom = 50.dp),
//
//    content = { Icon(Icons.Default.Add,"Add") },
//    contentColor = Color.Black,
//    containerColor = NtvhLightBlue,
//        shape = CircleShape
//    )
//},
//                //isFloatingActionButtonDocked = true,
//                floatingActionButtonPosition = FabPosition.End,
//            ) {
                //TestFunction()
                NavigationScreen()
//                }
            }
        }
    }
}


