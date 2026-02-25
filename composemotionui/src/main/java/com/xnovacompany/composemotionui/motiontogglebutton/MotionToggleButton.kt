package com.xnovacompany.composemotionui.motiontogglebutton

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MotionToggleButton(
//    onClick: ()-> Unit,
    contentOn: @Composable () -> Unit,
    contentOff: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    scrollDirection: Orientation = Orientation.Vertical
) {
    var isOn by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(50.dp)
            .clickable { isOn = !isOn },
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = isOn,
            transitionSpec = {
                slideInVertically { height -> height } togetherWith
                slideOutVertically { height -> -height }
            },
            label = ""
        ) { state ->
            if (state) {
                contentOn()
            } else {
                contentOff()
            }
        }
    }

}