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

package org.bharathvishal.randompasswordgenerator.Utilities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import org.bharathvishal.randompasswordgenerator.Constants.Constants

object Utilities {
    fun calculateStrengthOfPassword(password: String): String {
        val requiredLength = 8
        val maximumLength = 18

        val weakPassword = "Weak"
        val mediumPassword = "Medium"
        val strongPassword = "Strong"
        val veryStrongPassword = "Very Strong"

        var currentScore = 0
        var sawAUpperCaseChar = false
        var sawALowerCaseChar = false
        var sawADigit = false
        var sawASpecialChar = false
        val passWordLength = password.length

        for (i in 0 until passWordLength) {
            val curChar = password[i]
            if (curChar.isLetterOrDigit()) {
                if (curChar.isUpperCase() && !sawAUpperCaseChar) {
                    sawAUpperCaseChar = true
                    currentScore += 1
                }
                if (curChar.isLowerCase() && !sawALowerCaseChar) {
                    sawALowerCaseChar = true
                    currentScore += 1
                }
                if (curChar.isDigit() && !sawADigit) {
                    sawADigit = true
                    currentScore += 1
                }
            } else {
                if (!sawASpecialChar) {
                    currentScore += 1
                    sawASpecialChar = true
                }
            }
        }

        if (passWordLength > requiredLength) {
            currentScore += 1
        } else if (passWordLength > maximumLength) {
            currentScore += 1
        } else {
            currentScore = 0
        }

        when (currentScore) {
            0 -> return weakPassword
            1 -> return weakPassword
            2 -> return mediumPassword
            3 -> return mediumPassword
            4 -> return strongPassword
            5 -> return veryStrongPassword
            else -> {}
        }
        return veryStrongPassword
    }

    fun calculatePasswordStrengthColor(password: String): Color {
        val requiredLength = 8
        val maximumLength = 18

        var currentScore = 0
        var sawAUpperCaseChar = false
        var sawALowerCaseChar = false
        var sawADigit = false
        var sawASpecialChar = false
        val passWordLength = password.length

        for (i in 0 until passWordLength) {
            val curChar = password[i]
            if (curChar.isLetterOrDigit()) {
                if (curChar.isUpperCase() && !sawAUpperCaseChar) {
                    sawAUpperCaseChar = true
                    currentScore += 1
                }
                if (curChar.isLowerCase() && !sawALowerCaseChar) {
                    sawALowerCaseChar = true
                    currentScore += 1
                }
                if (curChar.isDigit() && !sawADigit) {
                    sawADigit = true
                    currentScore += 1
                }
            } else {
                if (!sawASpecialChar) {
                    currentScore += 1
                    sawASpecialChar = true
                }
            }
        }

        if (passWordLength > requiredLength) {
            currentScore += 1
        } else if (passWordLength > maximumLength) {
            currentScore += 1
        } else {
            currentScore = 0
        }

        when (currentScore) {
            0 -> return Constants.passwordColorWeak
            1 -> return Constants.passwordColorWeak
            2 -> return Constants.passwordColorMedium
            3 -> return Constants.passwordColorMedium
            4 -> return Constants.passwordColorStrong
            5 -> return Constants.passwordColorVeryStrong
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