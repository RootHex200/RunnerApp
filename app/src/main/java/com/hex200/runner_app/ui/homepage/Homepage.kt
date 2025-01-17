package com.hex200.runner_app.ui.homepage

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hex200.runner_app.R


@Composable
fun RunTrackerApp() {
    Scaffold (

        content = {padding->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0A1929))
                    .padding(16.dp)
            ) {
                AppHeader()
                HeartHealthSection()
                ActivityButtons()
                RecentActivities()

            }
        },
        bottomBar = {
            BottomNavigationBar()
        }
    )
}

@Composable
fun AppHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "RUN TRACKER",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Go Faster & Smarter",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
        }
        IconButton(onClick = { }) {
            Icon(
                Icons.Filled.Info,
                contentDescription = "Info",
                tint = Color.White
            )
        }
    }
}

@Composable
fun HeartHealthSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Heart Health",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = 100f,
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF2196F3),
                strokeWidth = 12.dp
            )
            Text(
                text = "100%",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ActivityMetric(
                icon=R.drawable.ic_person_walk,
                current = "0",
                target = "150",
                unit = "min"
            )
            ActivityMetric(
                icon=R.drawable.ic_person_run,
                current = "0",
                target = "75",
                unit = "min"
            )
        }
    }
}

@Composable
fun ActivityMetric(
    icon:Int,
    current: String,
    target: String,
    unit: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "$current/$target$unit",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ActivityButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround

    ) {
        Image(
            modifier = Modifier
                .height(130.dp)
                .width(150.dp),
            painter = painterResource(id = R.drawable.ic_steps), contentDescription ="step" )

        Image(
            modifier = Modifier
                .height(130.dp)
                .width(150.dp),
            painter = painterResource(id = R.drawable.ic_water), contentDescription ="water" )
    }
}

@Composable
fun RecentActivities() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Activities",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            TextButton(onClick = { }) {
                Text(
                    text = "More",
                    color = Color(0xFFE57373)
                )
            }
        }

        ActivityCard()
    }
}

@Composable
fun ActivityCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2196F3)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Image(
//                ,
//                contentDescription = "Activity Map",
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(RoundedCornerShape(8.dp))
//            )

            Column {
                Text(
                    text = "Nov 17, 2024",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "0.0 Km",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("00:00:17", color = Color.White)
                    Text("Infinity", color = Color.White)
                    Text("0.28 Kcal", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFF1E3246)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Rounded.Home, contentDescription = null) },
            selected = true,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f)
            )
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.high_intensity_icon),
                tint = Color.Unspecified
                , contentDescription = "") },
            selected = false,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Rounded.Person, contentDescription = null) },
            selected = false,
            onClick = { },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f)
            )
        )
    }
}
