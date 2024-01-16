package com.example.ntvhcalendar

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.ntvhcalendar.datacalendar.BottomNavItems
import com.example.ntvhcalendar.calendars.BottomNavgraph
import com.example.ntvhcalendar.ui.theme.BaseBGPrimary
import com.example.ntvhcalendar.ui.theme.BaseBGSecondary
import com.example.ntvhcalendar.ui.theme.NtvhBlue
import com.example.ntvhcalendar.ui.theme.NtvhCalendarTheme
import com.example.ntvhcalendar.ui.theme.NtvhGreen
import com.example.ntvhcalendar.ui.theme.NtvhLightBlue
import com.example.ntvhcalendar.ui.theme.NtvhRed
import com.example.ntvhcalendar.ui.theme.NtvhWhite

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

                val content = remember { mutableStateOf("Home Screen") }
                val selectedItem = remember { mutableStateOf("home") }
                val openDialog = remember { mutableStateOf(false) }

                val screens = listOf(
                    BottomNavItems.CompanyCalendar,
                    BottomNavItems.UserCalendar,
                    BottomNavItems.UserTask

                )
                val navController = rememberNavController()
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                Box {
                    Scaffold(

                        bottomBar = {
                            NavigationBar(
                                modifier = Modifier
                                    .scale(1f)
                                    .background(NtvhBlue)
                                //    .height(80.dp)
                                ,
                                contentColor = NtvhGreen,
                                containerColor = BaseBGSecondary
                            )
                            {
                                screens.forEachIndexed { index, item ->
                                    NavigationBarItem(


                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            navController.navigate(item.route)
                                        },
                                        label = {
                                            Text(text = item.name)
                                        },
                                        alwaysShowLabel = false,
                                        icon = {
                                            Icon(
                                                imageVector = if (index == selectedItemIndex) {
                                                    item.selectedIcon
                                                } else item.unselectedIcon,
                                                contentDescription = item.name
                                            )

                                        },

                                        )
                                }
                            }
                        }
                    ) {

                        BottomNavgraph(navController = navController)

                    }
                }
            }
        }
    }
}

//@Composable
//fun FloatingActionButton (context : Context) {
//    androidx.compose.material3.FloatingActionButton(
//        containerColor = NtvhBlue,
//        contentColor = NtvhWhite,
//        shape = CircleShape,
//        onClick = { Toast.makeText(context, "Нажал ADD", Toast.LENGTH_LONG).show() }) {
//        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Event")
//
//    }
//}