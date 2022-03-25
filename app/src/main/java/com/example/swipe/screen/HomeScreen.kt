package com.example.swipe.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swipe.R
import com.example.swipe.navigation.ScreenNavigation
import com.example.swipe.ui.theme.Purple200
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    var boxColor by remember { mutableStateOf(Purple200) }
    val color = animateColorAsState(
        targetValue = boxColor,
        animationSpec = tween(durationMillis = 2000)
    )
    val squareSize = 80.dp
    var swipeAbleState = rememberSwipeableState(initialValue = 0)
    val sizePX = with(LocalDensity.current) {
        squareSize.toPx()
    }
    val anchorsPoints = mapOf(0f to 0, sizePX to 1)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue.copy(alpha = 0.1f))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Compose Swipe",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .swipeable(
                    state = swipeAbleState,
                    anchors = anchorsPoints,
                    orientation = Orientation.Horizontal,
                    thresholds = { _, _ ->
                        FractionalThreshold(0.3f)
                    }
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    IconButton(
                        onClick = {
                            navController.navigate(route = ScreenNavigation.EditScreen.name)
                            boxColor = Color.Green
                        },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Color.Green
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    IconButton(
                        onClick = { boxColor = Color.Red },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .offset { IntOffset(swipeAbleState.offset.value.roundToInt(), 0) }
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .height(150.dp)
                        .fillMaxHeight()
                        .background(color.value)
                        .align(Alignment.CenterStart)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape),
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Column {
                                Text(
                                    text = "Swipe Layout",
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.padding(10.dp))

                                Text(
                                    text = "Lorem Ipsum is simply dummy text of the printing and type setting industry...",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}