package org.bharathvishal.randompasswordgenerator

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Random Password Generator - KMM",
    ) {
        App(
            darkTheme = isSystemInDarkTheme(),
            dynamicColor = false,
        )
    }
}