package org.bharathvishal.randompasswordgenerator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        var isDarkTheme = false
        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true, // dynamic color true for android)
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(true, true)
}