package org.bharathvishal.randompasswordgenerator.Utilities

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class SharedPrefsUtil {
    var settingsPref: Settings? = null

    fun InitSharedPrefs() {
        settingsPref = Settings()
    }

    fun getKeyValueFromPreferencesBoolean(strKey: String): Boolean? {
        return settingsPref?.getBoolean(strKey, false)
    }

    fun getKeyValueFromPreferencesString(strKey: String): String? {
        return settingsPref?.getString(strKey, "")
    }

    fun getKeyValueFromPreferencesInt(strKey: String): Int? {
        return settingsPref?.getInt(strKey, 5)
    }

    fun updateKeyValueToDataStoreBoolean(strKey: String, boolValue: Boolean) {
        try {
            settingsPref?.set(strKey, boolValue)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun updateKeyValueToDataStoreInt(strKey: String, intValue: Int) {
        try {
            settingsPref?.set(strKey, intValue)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateKeyValueToDataStoreString(strKey: String, strValue: String) {
        try {
            settingsPref?.set(strKey, strValue)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}