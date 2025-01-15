package com.bharathvishal.biometricauthentication.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Suppress("IMPLICIT_CAST_TO_ANY")
@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                systemUiController.setStatusBarColor(
                    color = DarkColorScheme.surface,
                    darkIcons = false
                )
                dynamicDarkColorScheme(context)
            } else {
                systemUiController.setStatusBarColor(
                    color = LightColorScheme.surface,
                    darkIcons = true
                )
                dynamicLightColorScheme(context)
            }
        }

        darkTheme -> {
            systemUiController.setStatusBarColor(color = DarkColorScheme.surface, darkIcons = false)
            DarkColorScheme
        }

        else -> {
            systemUiController.setStatusBarColor(
                color = LightColorScheme.surface,
                darkIcons = true
            )
            LightColorScheme
        }
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            //val window = (view.context as Activity).window
            //window.statusBarColor = colorScheme.surfaceContainer.toArgb()
            //WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}