package com.example.ntvhcalendar.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ntvhcalendar.ui.theme.BaseBGSecondary

@Preview(showBackground = true)
@Composable
fun ListItems() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = BaseBGSecondary, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Column(
                modifier = Modifier

                    .padding(start = 6.dp, top = 5.dp, bottom = 5.dp, end = 8.dp)
            )
            {
                ColorIndex(shape = RoundedCornerShape(5.dp), color = Color.Blue) // Изменение цвета События
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            )
            {

                 Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .width(300.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = "СОБЫТИЕ: Мама мыла раму, папа мыл dfghfh fghfghfgh  fg",
                        modifier = Modifier.wrapContentWidth())
                }


                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 5.dp, bottom = 5.dp, end = 8.dp)
                )
                {
                    Text(text = "12:30")
                    Text(text = "13:30")
                }
            }
        }

    }


}

@Composable
fun ColorIndex(shape: Shape, color: Color){
    Column(modifier = Modifier.wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier.height(40.dp).width(7.dp).clip(shape).background(color)
        )
    }
}

