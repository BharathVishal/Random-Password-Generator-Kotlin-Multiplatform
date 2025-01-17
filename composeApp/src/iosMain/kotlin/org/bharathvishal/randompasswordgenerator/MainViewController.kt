package org.bharathvishal.randompasswordgenerator

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import org.bharathvishal.randompasswordgenerator.Utilities.DATA_STORE_FILE_NAME
import org.bharathvishal.randompasswordgenerator.Utilities.createDataStore
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle


@OptIn(ExperimentalForeignApi::class)
fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        prefs = remember {
            createDataStore {
                val directory = NSFileManager.defaultManager.URLForDirectory(
                    directory = NSDocumentDirectory,
                    inDomain = NSUserDomainMask,
                    appropriateForURL = null,
                    create = false,
                    error = null
                )
                requireNotNull(directory).path + "/$DATA_STORE_FILE_NAME"
            }
        }
    )
}