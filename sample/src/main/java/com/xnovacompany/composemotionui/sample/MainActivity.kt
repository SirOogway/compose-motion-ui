package com.xnovacompany.composemotionui.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xnovacompany.composemotionui.motiontogglebutton.MotionToggleButton
import com.xnovacompany.composemotionui.sample.ui.theme.ComposeMotionUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMotionUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val text: @Composable ()-> Unit = { Text("Light", fontSize = 40.sp) }
                    val textAlt: @Composable ()-> Unit = { Text("Darkness", fontSize = 40.sp) }
                    val boxRed: @Composable ()-> Unit = {
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .background(Color.Red)
                        ) {}
                    }
                    val boxBlue: @Composable ()-> Unit = {
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .background(Color.Blue)
                        ) {}
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MotionToggleButton(
                            contentOn = text,
                            contentOff = textAlt,
                            modifier = Modifier.border(
                                width = 2.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(5.dp)
                            )
                        )
                    }
                }
            }
        }
    }
}