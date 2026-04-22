package com.divvvi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.divvvi.app.ui.DivvviApp
import com.divvvi.app.ui.theme.DivvviTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DivvviTheme {
                DivvviApp()
            }
        }
    }
}
