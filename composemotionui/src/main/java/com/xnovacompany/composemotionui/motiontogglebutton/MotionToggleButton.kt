package com.xnovacompany.composemotionui.motiontogglebutton

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MotionToggleButton(
    contentOn: @Composable () -> Unit,
    contentOff: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    scrollDirection: Orientation = Orientation.Vertical
) {
    val scrollState = rememberScrollState()
    val coroutinScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .background(Color.Red)
            .height(45.dp)
            .width(170.dp)
            .clickable {
                coroutinScope.launch() {
                    val target =
                        if (scrollState.value >= scrollState.maxValue / 2) 0 else scrollState.maxValue

                    scrollState.animateScrollTo(target)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        val scrollModifier =
            if (scrollDirection == Orientation.Horizontal)
                Modifier.horizontalScroll(scrollState)
            else Modifier.verticalScroll(scrollState)

        // La column no debe heredar el modifier ya que su configuración es de carácter interno
        if (scrollDirection == Orientation.Vertical)
            Column(
                modifier = Modifier
//                    .padding(0.dp)
                    .wrapContentSize()
                    .background(Color.Gray)
                    .then(scrollModifier),
//                verticalArrangement = Arrangement.spacedBy(110.dp)
            ) { contentOn(); contentOff() }
        else Row(modifier = modifier.then(scrollModifier)) { contentOn(); contentOff() }
    }

}