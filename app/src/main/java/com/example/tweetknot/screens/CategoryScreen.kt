package com.example.tweetknot.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tweetknot.R
import com.example.tweetknot.viewmodels.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    //when we integrate navigation framework with the view model then we have to use hiltViewModel()
//    other wise use viewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> = categoryViewModel.categories.collectAsState()

    //if categories are loading showing progress bar
    if (categories.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = createGradientBrush(
                        listOf(
                            Color(0xFF4568DC),
                            Color(0xFFB06AB3)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = createGradientBrush(
                        listOf(
                            Color(0xFF4568DC),
                            Color(0xFFB06AB3)
                        )
                    )
                )
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "TweetKnot",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                modifier = Modifier.background(Color.Green)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                items(categories.value.distinct())
                {
                    CategoryItem(category = it, onClick)
                }
            }
        }

    }
}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier =
        Modifier
            .padding(4.dp)
            .shadow(10.dp)
            .clickable {
                onClick(category)
            }
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .paint(
                painter = painterResource(id = R.drawable.bg7),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0xFF000000)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

private fun createGradientBrush(
    colors: List<Color>,
    isVertical: Boolean = true
): Brush {

    val endOffset = if (isVertical) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset(0f, 0f),
        end = endOffset,
        tileMode = TileMode.Mirror
    )
}