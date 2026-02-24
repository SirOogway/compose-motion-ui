package com.xnovacompany.composemotionui.motiontogglebutton

import android.content.res.Resources
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MotionToggleButton(
//    onClick: ()-> Unit,
    contentOn: @Composable () -> Unit,
    contentOff: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    scrollDirection: Orientation = Orientation.Vertical
) {
    val scrollState = rememberScrollState()
    val coroutinScope = rememberCoroutineScope()

    var heightDp by remember { mutableStateOf(0.dp) }
    var gapDp by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    /*    var isOn by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .height(40.dp)
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
        }*/


    Box(
        modifier = modifier
            .fillMaxWidth() // Ocupar la mitad de la altura por ser una tupla de contenido
            .height(80.dp)
            .onGloballyPositioned { coordinates ->
                heightDp = with(density) {
                    coordinates.size.height.toDp()
                }
                gapDp = heightDp/2
                Log.d("HEIGHT", "${heightDp}")
            }
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
                    .wrapContentSize()
                    .background(Color.Gray)
                    .then(scrollModifier),
                verticalArrangement = Arrangement.spacedBy(gapDp)
            ) { contentOn(); contentOff() }
        else Row(modifier = modifier.then(scrollModifier)) { contentOn(); contentOff() }
    }

}