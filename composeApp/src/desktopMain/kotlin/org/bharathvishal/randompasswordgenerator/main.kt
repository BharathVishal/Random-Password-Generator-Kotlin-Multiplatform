package org.bharathvishal.randompasswordgenerator

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.bharathvishal.randompasswordgenerator.Constants.Constants.APP_APPLICATION_NAME

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = APP_APPLICATION_NAME,
        state = WindowState(size = DpSize(1200.dp,1000.dp))
    ) {
        App(
            darkTheme = isSystemInDarkTheme(),
            dynamicColor = false
        )
    }
}