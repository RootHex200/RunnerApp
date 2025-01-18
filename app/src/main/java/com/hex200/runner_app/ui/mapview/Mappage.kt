package com.hex200.runner_app.ui.mapview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import com.hex200.runner_app.R
@Composable
fun RunTrackingScreen(
) {
    var isTracking by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableStateOf(0L) }
    var distance by remember { mutableStateOf(0.00) }
    var pace by remember { mutableStateOf(0.00) }
    var calories by remember { mutableStateOf(0.0) }

    LaunchedEffect(isTracking) {
        while (isTracking) {
            delay(1000)
            elapsedTime += 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1929))
    ) {

        // Timer Display
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatTime(elapsedTime),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Min",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.7f)
            )
        }

        // Metrics Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MetricDisplay(
                value = String.format("%.2f", distance),
                label = "KM"
            )
            MetricDisplay(
                value = String.format("%.2f", pace),
                label = "PACE (MIN/KM)"
            )
            MetricDisplay(
                value = String.format("%.1f", calories),
                label = "KCAL"
            )
        }

        // Map View
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            GoogleMapView()

            // Start Button
            Button(
                onClick = { isTracking = !isTracking },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7B7B)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = if (isTracking) "STOP" else "START",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                )
            }

            // Zoom Controls
            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp, end = 20.dp)
                    .height(50.dp)
                    .width(40.dp)
                    .background(color = Color.White)
                    .align(Alignment.BottomEnd),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Icon(
                    modifier = Modifier.clickable {
                        //TODO: google map  zoom-out
                    },
                    painter = painterResource(id = R.drawable.ic_add), tint = Color.Black, contentDescription = "")
                Spacer(modifier = Modifier.height(15.dp))
                Icon(
                    modifier = Modifier.clickable {
                        //TODO: google map zoom-in
                    },
                    painter = painterResource(id = R.drawable.ic_minus), tint = Color.Black, contentDescription = "")
            }
        }
    }
}

@Composable
fun MetricDisplay(
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun GoogleMapView() {
    var mapView: MapView? by remember { mutableStateOf(null) }

    AndroidView(
        factory = { context ->
            MapView(context).apply {
                mapView = this
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { view ->
            mapView?.onCreate(null)
            mapView?.getMapAsync { googleMap ->
                val initialPosition = LatLng(0.0, 0.0) // Replace with actual location
                val cameraPosition = CameraPosition.Builder()
                    .target(initialPosition)
                    .zoom(15f)
                    .build()
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
        }
    )

    DisposableEffect(Unit) {
        onDispose {
            mapView?.onDestroy()
        }
    }
}

private fun formatTime(seconds: Long): String {
    return String.format(
        "%02d:%02d:%02d",
        TimeUnit.SECONDS.toHours(seconds),
        TimeUnit.SECONDS.toMinutes(seconds) % 60,
        seconds % 60
    )
}

@Preview
@Composable
fun mapviewpreview(){
    RunTrackingScreen()
}