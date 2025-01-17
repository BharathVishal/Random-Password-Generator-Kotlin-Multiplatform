package org.bharathvishal.randompasswordgenerator.Constants

import androidx.compose.ui.graphics.Color


object Constants {
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
}

