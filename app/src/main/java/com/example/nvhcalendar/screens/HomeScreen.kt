package com.example.nvhcalendar.screens


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.nvhcalendar.ui.theme.NvhCalendarTheme


@Composable
fun HomeScreen()
{

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