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

package org.bharathvishal.randompasswordgenerator.constants

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle


object Constants {
    const val MAX_LENGTH = 45
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

    const val SELECTED_THEME = "SELECTED_THEME"
    const val SELECTED_THEME_NIGHT = "NIGHT_MODE"
    const val SELECTED_THEME_LIGHT = "LIGHT_MODE"

    const val ANIMATION_SETTING = "ANIMATION_SETTING"

    const val PLATFORM_WEB = "Web with Kotlin/Wasm"
    const val APP_CLOSE = "Close"
    const val APP_APPLICATION_NAME = "Random Password Generator"
    const val APP_TITLE_NAME = "Random Password Generator"
    const val APP_VERSION = "v1.0"
    const val APP_ABOUT = "About App"
    const val APP_HELP = "Help"
    const val APP_CREDITS =
        "© 2025. Developed by Bharath Vishal. Open Source Software licensed with Apache 2.0 license."


    const val HUNDRED_RANDOM_PASSWORDS_GENERATED = "Generated one hundred random passwords."
    const val RANDOM_PASSWORD_GENERATED = "Random password generated."
    const val SNACKBARMESSAGE_VAL_EMPTY = "-"
    const val DEFAULT_PASSWORD_VALUE = "ABCDEFGHIJK"
    const val GENERATE_PASSWORD = "Generate Password"
    const val GENERATE_HUNDRED_PASSWORD = "Generate Hundred Random Passwords"
    const val COPIED_PASSWORD_TO_CLIPBOARD = "Copied password to Clipboard."
    const val COPY_PASSWORD = "Copy Password"
    const val PASSWORD_STRENGTH = "Password Strength"

    const val PASSWORD_GENERATION_ANIMATION_ON="Enabled password generation animation."
    const val PASSWORD_GENERATION_ANIMATION_OFF="Disabled password generation animation."

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
        "]",
        "_"
    )

    const val SELECT_ATLEAST_ONE_CONSTRAINT_TO_GENERATE_STRONGER_PASSWORD =
        "Select at least one constraint to generate a stronger password."
    const val LENGTH_SHOULD_NOT_BE_EMPTY = "Length should not be empty"
    const val PASSWORD_MIN_LENGTH_CONSTRAINT =
        "Password should be atleast $MIN_LENGTH characters in length"
    const val PASSWORD_MAX_LENGTH_CONSTRAINT =
        "Password should be atleast $MIN_LENGTH characters in length"

    const val APACHE_LICENSE_LINK = "https://github.com/apache/.github/blob/main/LICENSE"
    const val LICENSE_TAG = "license"

    const val GENERATED_RANDOM_PASSWORDS = "Generated Random Passwords"
    const val APP_ABOUT_MAIN = "© 2025. Developed by Bharath Vishal. "

    const val APP_HELP_1 = "Generate strong random passwords with this application. Generate passwords with multiple available options."
    const val APP_HELP_2 = "Adjusting the password length slider automatically generates new passwords. Features automatic dark mode switching and Material Dynamic theming on Android."

    val annotatedStringCredits = buildAnnotatedString {
        append("Open Source Software licensed with ")

        pushStringAnnotation(
            tag = LICENSE_TAG,
            annotation = APACHE_LICENSE_LINK
        )
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append("Apache 2.0 license")
        }
        pop()
        append(".")
    }
}

