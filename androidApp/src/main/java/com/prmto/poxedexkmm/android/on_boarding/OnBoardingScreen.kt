package com.prmto.poxedexkmm.android.on_boarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.prmto.poxedexkmm.android.R
import com.prmto.poxedexkmm.on_boarding.presentation.OnBoardingData
import com.prmto.poxedexkmm.on_boarding.presentation.getOnBoardingData
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onNavigateToHome: () -> Unit,
    onBoardingData: List<OnBoardingData>
) {
    val pagerState = rememberPagerState()

    val pageCount = onBoardingData.size
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding),
                    pageCount = pageCount
                ) {
                    HorizontalPagerItem(
                        onBoardingData = getOnBoardingData()[it],
                    )
                }

                PagerIndicator(
                    pageCount = pageCount,
                    currentPage = pagerState.currentPage
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (pagerState.currentPage < pageCount - 1) {
                            scope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            onNavigateToHome()
                        }
                    },
                    modifier = Modifier.clip(RoundedCornerShape(50))
                ) {
                    Text(
                        text = stringResource(R.string.continues),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row {
        repeat(pageCount) {
            if (it == currentPage) {
                SelectedPagerIndicator()
            } else {
                UnSelectedPagerIndicator()
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun SelectedPagerIndicator() {
    Box(
        modifier = Modifier
            .width(28.dp)
            .height(9.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colors.primary)
    )
}

@Composable
fun UnSelectedPagerIndicator() {
    Box(
        modifier = Modifier
            .width(9.dp)
            .height(9.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(Color(0xFF4565B7))
    )
}


@Composable
fun HorizontalPagerItem(
    modifier: Modifier = Modifier,
    onBoardingData: OnBoardingData
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = onBoardingData.image),
            contentDescription = onBoardingData.title,
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = onBoardingData.title,
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = onBoardingData.description,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}