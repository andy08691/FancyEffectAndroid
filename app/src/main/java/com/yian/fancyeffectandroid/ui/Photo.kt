package com.yian.fancyeffectandroid.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay

@Composable
fun Photo(source: Int, bgSource: Int, isVisible: Boolean) {
    val picture = painterResource(id = source)
    val brokenEffectBackground = painterResource(id = bgSource)
    PictureWithBrokenEffect(
        picture = picture,
        brokenEffectBackground = brokenEffectBackground,
        isVisible = isVisible
    )
}


@Composable
fun PictureWithBrokenEffect(
    picture: Painter, // Dog's picture as a Painter
    brokenEffectBackground: Painter, // Broken effect PNG as a Painter
    isVisible: Boolean,
) {
    var isZoomed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if(isZoomed) 1.5f else 1f,
        animationSpec = tween(durationMillis = 500)
    )
    LaunchedEffect(isVisible) {
        if (isVisible) {
            isZoomed = true
        } else {
            isZoomed = false
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { isZoomed = !isZoomed }
    ) {
        // Broken effect background
        Image(
            painter = brokenEffectBackground,
            contentDescription = "Broken effect background",
            modifier = Modifier.size(300.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        // Center circular dog picture
        Image(
            painter = picture,
            contentDescription = "Dog picture",
            modifier = Modifier
                .size(200.dp) // Adjust size as needed
                .zIndex(1f)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
            ,
            contentScale = ContentScale.Fit
        )
    }
}