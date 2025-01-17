package org.bharathvishal.randompasswordgenerator

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.bharathvishal.randompasswordgenerator.Utilities.DATA_STORE_FILE_NAME
import org.bharathvishal.randompasswordgenerator.Utilities.createDataStore

fun createDataStore(context: Context): DataStore<Preferences> {
    return createDataStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}
