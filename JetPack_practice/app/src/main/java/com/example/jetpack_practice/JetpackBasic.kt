package com.example.jetpack_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class JetpackBasic : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                verticalArrangement=Arrangement.SpaceAround,
                horizontalAlignment=Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Red)
            ){
                Row(
                    horizontalArrangement=Arrangement.Center,
                    verticalAlignment=Alignment.CenterVertically,
                    modifier = Modifier.background(color = Color.Green)
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                ) {
                    Text(text = "hi")
                    Text(text = "hello")

                }
                Row(
                    horizontalArrangement=Arrangement.SpaceBetween,
                    verticalAlignment=Alignment.Bottom,
                    modifier = Modifier.background(color = Color.Green)
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                ) {
                    Text(text = "hi")
                    Text(text = "hello")
                }
                Row(
                    horizontalArrangement=Arrangement.SpaceEvenly,
                    verticalAlignment=Alignment.Top,
                    modifier = Modifier.background(color = Color.Green)
                        .width(400.dp)
                        .height(200.dp)
                ) {
                    Text(text = "hi")
                    Text("hello" ,modifier = Modifier.offset(x=50.dp,y=25.dp) )
                }

            }
        }
    }
}


