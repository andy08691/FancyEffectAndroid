package com.yian.fancyeffectandroid.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yian.fancyeffectandroid.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGallery(modifier: Modifier, mainViewModel: MainViewModel = viewModel()) {
    val pictures = mainViewModel.pictures.collectAsState()
    val bg = mainViewModel.bg.collectAsState().value
    val pagerState = rememberPagerState(pageCount = { pictures.value.size })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            page ->
                Log.d("page change", "$page")
        }
    }

    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState) { page ->
            // Our page content
            Photo(pictures.value[page], bg,  pagerState.currentPage == page)
        }
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            Modifier.wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) {
                iteration ->
                val color = if (pagerState.currentPage == iteration) Color.Gray else Color.LightGray
                Box(modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
                )
            }
        }

    }

}