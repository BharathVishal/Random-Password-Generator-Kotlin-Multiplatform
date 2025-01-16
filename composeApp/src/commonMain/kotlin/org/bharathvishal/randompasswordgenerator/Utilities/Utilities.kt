package org.bharathvishal.randompasswordgenerator.Utilities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import org.bharathvishal.randompasswordgenerator.Constants.Constants

object Utilities {
    fun calculateStrengthOfPassword(password: String): String {
        val REQUIRED_LENGTH = 8
        val MAXIMUM_LENGTH = 20
        val REQUIRE_SPECIAL_CHARACTERS = true
        val REQUIRE_DIGITS = true
        val REQUIRE_LOWER_CASE = true
        val REQUIRE_UPPER_CASE = true

        val WEAK = "Weak"
        val MEDIUM = "Medium"
        //Color.argb(255, 220, 185, 0)),
        val STRONG = "Strong"
        val VERY_STRONG = "Very Strong"

        var currentScore = 0
        var sawUpper = false
        var sawLower = false
        var sawDigit = false
        var sawSpecial = false
        for (i in 0 until password.length) {
            val c = password[i]
            if (!sawSpecial && !c.isLetterOrDigit()) {
                currentScore += 1
                sawSpecial = true
            } else {
                if (!sawDigit && c.isDigit()) {
                    currentScore += 1
                    sawDigit = true
                } else {
                    if (!sawUpper || !sawLower) {
                        if (c.isUpperCase()) sawUpper = true else sawLower = true
                        if (sawUpper && sawLower) currentScore += 1
                    }
                }
            }
        }
        if (password.length > REQUIRED_LENGTH) {
            if (REQUIRE_SPECIAL_CHARACTERS && !sawSpecial || REQUIRE_UPPER_CASE && !sawUpper || REQUIRE_LOWER_CASE && !sawLower || REQUIRE_DIGITS && !sawDigit) {
                currentScore = 1
            } else {
                currentScore = 2
                if (password.length > MAXIMUM_LENGTH) {
                    currentScore = 3
                }
            }
        } else {
            currentScore = 0
        }
        when (currentScore) {
            0 -> return WEAK
            1 -> return MEDIUM
            2 -> return STRONG
            3 -> return VERY_STRONG
            else -> {}
        }
        return VERY_STRONG
    }


    fun calculatePasswordStrengthColor(password: String): Color {
        val REQUIRED_LENGTH = 8
        val MAXIMUM_LENGTH = 20
        val REQUIRE_SPECIAL_CHARACTERS = true
        val REQUIRE_DIGITS = true
        val REQUIRE_LOWER_CASE = true
        val REQUIRE_UPPER_CASE = true

        val WEAK = "Weak"
        val MEDIUM = "Medium"
        //Color.argb(255, 220, 185, 0)),
        val STRONG = "Strong"
        val VERY_STRONG = "Very Strong"

        var currentScore = 0
        var sawUpper = false
        var sawLower = false
        var sawDigit = false
        var sawSpecial = false
        for (i in 0 until password.length) {
            val c = password[i]
            if (!sawSpecial && !c.isLetterOrDigit()) {
                currentScore += 1
                sawSpecial = true
            } else {
                if (!sawDigit && c.isDigit()) {
                    currentScore += 1
                    sawDigit = true
                } else {
                    if (!sawUpper || !sawLower) {
                        if (c.isUpperCase()) sawUpper = true else sawLower = true
                        if (sawUpper && sawLower) currentScore += 1
                    }
                }
            }
        }
        if (password.length > REQUIRED_LENGTH) {
            if (REQUIRE_SPECIAL_CHARACTERS && !sawSpecial || REQUIRE_UPPER_CASE && !sawUpper || REQUIRE_LOWER_CASE && !sawLower || REQUIRE_DIGITS && !sawDigit) {
                currentScore = 1
            } else {
                currentScore = 2
                if (password.length > MAXIMUM_LENGTH) {
                    currentScore = 3
                }
            }
        } else {
            currentScore = 0
        }
        when (currentScore) {
            0 -> return Constants.passwordColorWeak
            1 -> return Constants.passwordColorMedium
            2 -> return Constants.passwordColorStrong
            3 -> return Constants.passwordColorVeryStrong
            else -> {}
        }
        return Constants.passwordColorVeryStrong
    }


    fun createSpannableTextColor(
        inputText: String,
        startIndex: Int,
        endIndex: Int,
        textColor: Color
    ): AnnotatedString {
        var strBef = ""
        var span = ""
        var end = ""
        for (i in 0 until startIndex) {
            strBef += inputText[i]
        }

        for (i in startIndex until endIndex)
            span += inputText[i]

        for (i in endIndex until inputText.length)
            end += inputText[i]

        val annotatedString = buildAnnotatedString {
            append(strBef)
            withStyle(style = SpanStyle(textColor)) {
                append(span)
            }
            append(end)
        }
        return annotatedString
    }

}