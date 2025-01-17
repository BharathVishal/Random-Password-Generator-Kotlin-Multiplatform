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

package org.bharathvishal.randompasswordgenerator.Constants

import androidx.compose.ui.graphics.Color


object Constants {
    const val MAX_LENGTH = 30
    const val MIN_LENGTH = 5

    const val PASSWORD_LENGTH = "Password Length"
    const val INCLUDE_CAPITAL_LETTERS = "Include capital letters (A ,B, C)"
    const val INCLUDE_SMALL_LETTERS = "Include small letters (a ,b, c)"
    const val INCLUDE_NUMBERS = "Include numbers (1 ,2, 3)"
    const val INCLUDE_SYMBOLS = "Include symbols (@ ,!, %)"

    val passwordColorWeak = Color(0xFFC0392B)
    val passwordColorMedium = Color(0xFFFFBF00)
    val passwordColorStrong = Color(0xFF28B463)
    val passwordColorVeryStrong = Color(0xFF3399CC)

    const val prefsIncludeCapLetters = "IncludeCapLetters"
    const val prefsIncludeSmallLetters = "IncludeSmallLetters"
    const val prefsIncludeNumbers = "IncludeNumbers"
    const val prefsIncludeSymbols = "IncludeSymbols"
    const val prefsPasswordLength = "PassLength"
    const val prefsPasswordSavedOld = "OldPassword"
    const val prefsPasswordSavedOldStr = "OldPasswordStr"
    const val prefsSavedTheme = "prefsSavedTheme"

    const val SELECTED_THEME="SELECTED_THEME"
    const val SELECTED_THEME_NIGHT="NIGHT_MODE"
    const val SELECTED_THEME_LIGHT="LIGHT_MODE"

    const val PLATFORM_WEB = "Web with Kotlin/Wasm"
    const val APP_CLOSE = "Close"
    const val APP_APPLICATION_NAME = "Random Password Generator - KMM"
    const val APP_TITLE_NAME = "Random Password Generator"
    const val APP_ABOUT = "About App"
    const val APP_CREDITS =
        "© 2025. Developed by Bharath Vishal. Open Source Software licensed with Apache 2.0 license."

    const val RANDOM_PASSWORD_GENERATED = "Random password generated."
    const val SNACKBARMESSAGE_VAL_EMPTY = "-"
    const val DEFAULT_PASSWORD_VALUE = "ABCDEFGHIJK"
    const val GENERATE_PASSWORD = "Generate Password"
    const val COPIED_PASSWORD_TO_CLIPBOARD = "Copied password to Clipboard."
    const val COPY_PASSWORD = "Copy Password"
    const val PASSWORD_STRENGTH = "Password Strength"

    val SYMBOLS_LIST = arrayOf(
        "#",
        "&",
        "/",
        "-",
        "+",
        "%",
        "$",
        "<",
        ">",
        "(",
        ")",
        "{",
        "}",
        "[",
        "]"
    )

    const val SELECT_ATLEAST_ONE_CONSTRAINT_TO_GENERATE_STRONGER_PASSWORD =
        "Select at least one constraint to generate a stronger password."
    const val LENGTH_SHOULD_NOT_BE_EMPTY = "Length should not be empty"
    const val PASSWORD_MIN_LENGTH_CONSTRAINT =
        "Password should be atleast $MIN_LENGTH characters in length"
    const val PASSWORD_MAX_LENGTH_CONSTRAINT =
        "Password should be atleast $MIN_LENGTH characters in length"
}

