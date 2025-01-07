package org.bharathvishal.randompasswordgenerator

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Random Password Generator",
    ) {
        App()
    }
}