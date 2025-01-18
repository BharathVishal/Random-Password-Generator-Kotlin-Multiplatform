/**
 *
 * Copyright 2025 Bharath Vishal G.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/

package org.bharathvishal.randompasswordgenerator.utilities

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class SharedPrefsUtil {
    private var settingsPref: Settings? = null

    fun initSharedPrefs() {
        settingsPref = Settings()
    }

    fun getKeyValueFromPreferencesBoolean(strKey: String): Boolean? {
        return settingsPref?.getBoolean(strKey, false)
    }

    fun getKeyValueFromPreferencesString(strKey: String): String? {
        return settingsPref?.getString(strKey, "Password")
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