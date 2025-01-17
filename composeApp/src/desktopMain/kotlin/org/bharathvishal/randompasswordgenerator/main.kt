package org.bharathvishal.randompasswordgenerator

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.bharathvishal.randompasswordgenerator.Utilities.DATA_STORE_FILE_NAME
import org.bharathvishal.randompasswordgenerator.Utilities.createDataStore

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Random Password Generator - KMM",
        state = WindowState(size = DpSize(1200.dp,1000.dp))
    ) {
        App(
            darkTheme = isSystemInDarkTheme(),
            dynamicColor = false,
            prefs = createDataStore {
                DATA_STORE_FILE_NAME
            }
        )
    }
}