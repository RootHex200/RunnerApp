package com.hex200.runner_app.ui.mapview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hex200.runner_app.R
import com.hex200.runner_app.core.ui.theme.RunnerAppTheme

class Mapview : AppCompatActivity() {
    lateinit var composeview:ComposeView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapview)

        composeview=findViewById(R.id.mapcomposerVIewID)
        composeview.setContent {
            RunnerAppTheme {

            }
        }

    }
}