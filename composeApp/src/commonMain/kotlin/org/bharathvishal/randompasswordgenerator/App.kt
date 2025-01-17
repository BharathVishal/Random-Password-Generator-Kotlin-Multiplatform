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

package org.bharathvishal.randompasswordgenerator


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharathvishal.biometricauthentication.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bharathvishal.randompasswordgenerator.Constants.Constants
import org.bharathvishal.randompasswordgenerator.Utilities.SharedPrefsUtil
import org.bharathvishal.randompasswordgenerator.Utilities.Utilities
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import randompasswordgenerator.composeapp.generated.resources.Res
import randompasswordgenerator.composeapp.generated.resources.baseline_password_24
import randompasswordgenerator.composeapp.generated.resources.dark_mode_24dp
import randompasswordgenerator.composeapp.generated.resources.info_24dp
import randompasswordgenerator.composeapp.generated.resources.light_mode_24dp

private var showSnackBarVal = mutableStateOf(false)
private var snackBarMessageVal = mutableStateOf("-")
private var passwordTextVal = mutableStateOf(Constants.DEFAULT_PASSWORD_VALUE)
private var passwordLengthVal = mutableStateOf(Constants.MIN_LENGTH)
val mCheckedStateCapitalLetters = mutableStateOf(false)
val mCheckedStateSmallLetters = mutableStateOf(false)
val mCheckedStateNumbers = mutableStateOf(false)
val mCheckedStateSymbol = mutableStateOf(false)
private val copyToClipboard = mutableStateOf(false)
val mPasswordStrength = mutableStateOf("Weak")
var anotatedStringPasswordStrength: AnnotatedString? = null
var thumbThemeSelected = mutableStateOf(false)
var isDarkTheme = mutableStateOf(false)
var isThemeIconVisible = mutableStateOf(false)
var shouldShowDialogAbout = mutableStateOf(false)

private val capital_letters_array = arrayOfNulls<String>(26)
private val small_letters_array = arrayOfNulls<String>(26)
private val numbers_array = arrayOfNulls<String>(10)
private lateinit var symbols_array: Array<String>
private var initialisedValues = false
private val settingsPrefs: SharedPrefsUtil = SharedPrefsUtil()
private var curSelectedTheme = Constants.SELECTED_THEME_NIGHT

@Composable
@Preview
fun App(
    darkTheme: Boolean, dynamicColor: Boolean
) {
    settingsPrefs.InitSharedPrefs()
    println("App Initialised with Settings Pref.")

    val prefsIncludeCapLetters =
        settingsPrefs.getKeyValueFromPreferencesBoolean(Constants.prefsIncludeCapLetters)
    val prefsIncludeSmallLetters =
        settingsPrefs.getKeyValueFromPreferencesBoolean(Constants.prefsIncludeSmallLetters)
    val prefsIncludeNumbers =
        settingsPrefs.getKeyValueFromPreferencesBoolean(Constants.prefsIncludeNumbers)
    val prefsIncludeSymbols =
        settingsPrefs.getKeyValueFromPreferencesBoolean(Constants.prefsIncludeSymbols)
    val prefsPasswordLength =
        settingsPrefs.getKeyValueFromPreferencesInt(Constants.prefsPasswordLength)
    val prefsPasswordSavedOld =
        settingsPrefs.getKeyValueFromPreferencesString(Constants.prefsPasswordSavedOld)
    val prefsPasswordSavedOldStr =
        settingsPrefs.getKeyValueFromPreferencesString(Constants.prefsPasswordSavedOldStr)
    val prefsSavedTheme =
        settingsPrefs.getKeyValueFromPreferencesString(Constants.SELECTED_THEME)

    mCheckedStateCapitalLetters.value = prefsIncludeCapLetters.toString().toBoolean()
    mCheckedStateSmallLetters.value = prefsIncludeSmallLetters.toString().toBoolean()
    mCheckedStateNumbers.value = prefsIncludeNumbers.toString().toBoolean()
    mCheckedStateSymbol.value = prefsIncludeSymbols.toString().toBoolean()
    if (prefsPasswordLength != null) {
        passwordLengthVal.value = prefsPasswordLength
    }
    if (prefsPasswordSavedOld != null) {
        passwordTextVal.value = prefsPasswordSavedOld
    }
    if (prefsPasswordSavedOldStr != null) {
        mPasswordStrength.value = prefsPasswordSavedOldStr
    }
    if (prefsSavedTheme != null) {
        curSelectedTheme = prefsSavedTheme
    }

    AppTheme(
        darkTheme = (if (isSystemInDarkTheme()) {
            isThemeIconVisible.value = false
            darkTheme
        } else {
            isThemeIconVisible.value = true
            isDarkTheme.value
        }), dynamicColor = dynamicColor
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            MainViewImplementation()
        }
    }
}

@Composable
fun MainViewImplementation() {
    Scaffold(topBar = { TopAppBarMain() }) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier.padding(1.dp).fillMaxWidth().fillMaxHeight()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                CardViewMain()
                Box(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                        .offset(y = (-50).dp)
                        .align(Alignment.BottomStart)
                ) {
                    SnackBarViewComposable(showSnackBarVal.value, snackBarMessageVal.value)
                    if (!initialisedValues) {
                        initValues()
                        initialisedValues = true
                    }
                    if (shouldShowDialogAbout.value) {
                        AlertDialogAbout()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMain() {
    TopAppBar(
        title = { Text(Constants.APP_APPLICATION_NAME) },
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun CardViewMain() {
    Column {
        Spacer(modifier = Modifier.padding(top = 6.dp))
        Card(
            modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            colors = CardDefaults.cardColors(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ImageLogo()
                TextHeader()
                RowComponentImageButtons()
                Divider(thickness = 0.5.dp)
                RowComponentPasswordLabel(passwordTextVal.value)
                RowComponentPasswordStrength()
                RowComponentButtons()
                Divider(thickness = 0.5.dp)
                RowComponentInCardSlider(Constants.PASSWORD_LENGTH)
                Divider(thickness = 0.5.dp)
                RowComponentInCardSwitch(
                    Constants.INCLUDE_CAPITAL_LETTERS,
                    mCheckedStateCapitalLetters.value
                )
                Divider(thickness = 0.5.dp)
                RowComponentInCardSwitch(
                    Constants.INCLUDE_SMALL_LETTERS,
                    mCheckedStateSmallLetters.value
                )
                Spacer(modifier = Modifier.padding(top = 6.dp))
                RowComponentInCardSwitch(Constants.INCLUDE_NUMBERS, mCheckedStateNumbers.value)
                Spacer(modifier = Modifier.padding(top = 6.dp))
                RowComponentInCardSwitch(Constants.INCLUDE_SYMBOLS, mCheckedStateSymbol.value)
                Spacer(modifier = Modifier.padding(top = 6.dp))
            }//end of column
        }//end of card
    }//end of outer column
}//end of card view main


@Composable
fun ImageLogo() {
    Image(
        painter = painterResource(Res.drawable.baseline_password_24),
        contentDescription = "Image Logo",
        modifier = Modifier.requiredHeight(100.dp).requiredWidth(100.dp).padding(1.dp)
    )
}

@Composable
fun ImageButtonTheme(visibility: Boolean) {
    val res: DrawableResource = if (thumbThemeSelected.value) {
        Res.drawable.light_mode_24dp
    } else {
        Res.drawable.dark_mode_24dp
    }

    if (visibility) {
        IconButton(
            onClick = {
                isDarkTheme.value = !thumbThemeSelected.value
                thumbThemeSelected.value = !thumbThemeSelected.value

                if (isDarkTheme.value)
                    settingsPrefs.updateKeyValueToDataStoreString(
                        Constants.SELECTED_THEME,
                        Constants.SELECTED_THEME_NIGHT
                    )
                else
                    settingsPrefs.updateKeyValueToDataStoreString(
                        Constants.SELECTED_THEME,
                        Constants.SELECTED_THEME_LIGHT
                    )
            }
        ) {
            Image(
                painter = painterResource(res),
                contentDescription = "Theme switch",
                modifier = Modifier.requiredHeight(35.dp).requiredWidth(35.dp).padding(1.dp)
            )
        }
    }
}


@Composable
fun ImageButtonAbout() {
    IconButton(
        onClick = {
            shouldShowDialogAbout.value = true
        }
    ) {
        Image(
            painter = painterResource(Res.drawable.info_24dp),
            contentDescription = "About switch",
            modifier = Modifier.requiredHeight(35.dp).requiredWidth(35.dp).padding(1.dp)
        )
    }
}


//Biometric Authentication app name Text
@Composable
fun TextHeader() {
    Text(
        text = Constants.APP_TITLE_NAME,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(1.dp).fillMaxWidth(),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun AlertDialogAbout() {
    if (shouldShowDialogAbout.value) {
        val annotatedText = Constants.annotatedStringCredits
        val localUriHandler = LocalUriHandler.current
        AlertDialog(
            onDismissRequest = {
                shouldShowDialogAbout.value = false
            },
            title = {
                Text(
                    text = Constants.APP_ABOUT_MAIN, style = MaterialTheme.typography.labelMedium,
                    fontSize = 25.sp,
                    lineHeight = 25.sp
                )
            },
            text = {
                Text(
                    modifier = Modifier.clickable {
                        val annotation = annotatedText.getStringAnnotations(
                            tag = Constants.LICENSE_TAG,
                            start = 0,
                            end = annotatedText.length - 1
                        ).firstOrNull()

                        if (annotation != null) {
                            localUriHandler.openUri(Constants.APACHE_LICENSE_LINK)
                        }
                    },
                    text = Constants.annotatedStringCredits,
                    style = TextStyle.Default,
                    fontSize = 19.sp,
                    lineHeight = 19.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialogAbout.value = false
                    }
                ) {
                    Text(
                        text = Constants.APP_CLOSE,
                    )
                }
            }
        )
    }
}

@Composable
fun SnackBarViewComposable(visibilityState: Boolean, message: String) {
    if (visibilityState) {
        AnimatedVisibility(
            visible = visibilityState,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(250))
        ) {
            Snackbar(action = {}, modifier = Modifier.fillMaxWidth()) {
                Text(text = message)
            }
        }
    }
}


//Row component composable function for Biometric related info
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowComponentInCardSlider(strDesc: String) {
    var sliderPosition by remember { mutableFloatStateOf(5f) }
    val haptic = LocalHapticFeedback.current
    sliderPosition = passwordLengthVal.value.toFloat()
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = strDesc,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Slider(
            value = sliderPosition,
            onValueChange = {
                try {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                sliderPosition = it.toInt().toFloat()
                passwordLengthVal.value = it.toInt()
                generateRandomPassword()
            },
            steps = 30, valueRange = 5f..30f,
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    thumbSize = DpSize(25.dp, 25.dp)
                )
            },
        )
        Text(text = sliderPosition.toInt().toString())
    }
}


@Composable
fun RowComponentInCardSwitch(strDesc: String, boolValue: Boolean) {
    val mCheckedState = remember { mutableStateOf(false) }
    mCheckedState.value = boolValue

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = strDesc,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Switch(checked = mCheckedState.value, onCheckedChange = {
            mCheckedState.value = it

            when (strDesc) {
                Constants.INCLUDE_CAPITAL_LETTERS -> {
                    mCheckedStateCapitalLetters.value = mCheckedState.value
                    settingsPrefs.updateKeyValueToDataStoreBoolean(
                        Constants.prefsIncludeCapLetters,
                        mCheckedState.value
                    )
                }

                Constants.INCLUDE_SMALL_LETTERS -> {
                    mCheckedStateSmallLetters.value = mCheckedState.value
                    settingsPrefs.updateKeyValueToDataStoreBoolean(
                        Constants.prefsIncludeSmallLetters,
                        mCheckedState.value
                    )
                }

                Constants.INCLUDE_NUMBERS -> {
                    mCheckedStateNumbers.value = mCheckedState.value
                    settingsPrefs.updateKeyValueToDataStoreBoolean(
                        Constants.prefsIncludeNumbers,
                        mCheckedState.value
                    )
                }

                Constants.INCLUDE_SYMBOLS -> {
                    mCheckedStateSymbol.value = mCheckedState.value
                    settingsPrefs.updateKeyValueToDataStoreBoolean(
                        Constants.prefsIncludeSymbols,
                        mCheckedState.value
                    )
                }
            }
        })
    }
}


@Composable
fun RowComponentButtons() {
    var clipboardManager: ClipboardManager? = null
    val curPlatforn = getPlatform().name

    if (curPlatforn != Constants.PLATFORM_WEB) {
        clipboardManager = LocalClipboardManager.current
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1F),
            onClick = {
                generateRandomPassword()
            },
            contentPadding = PaddingValues(
                start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp
            ),
        ) {
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = Constants.GENERATE_PASSWORD)
        }

        OutlinedButton(
            modifier = Modifier.weight(1F),
            onClick = {
                copyToClipboard.value = true
                showSnackBarCoroutine(Constants.COPIED_PASSWORD_TO_CLIPBOARD)
            },
            contentPadding = PaddingValues(
                start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp
            ),
        ) {
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = Constants.COPY_PASSWORD)
            if (curPlatforn != Constants.PLATFORM_WEB) {
                clipboardManager!!.setText(AnnotatedString(passwordTextVal.value))
            }
        }
    }
}


@Composable
fun RowComponentPasswordLabel(strPass: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = strPass,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 30.sp,
            lineHeight = 35.sp
        )
    }
}

@Composable
fun RowComponentPasswordStrength() {
    val passStr = Utilities.calculateStrengthOfPassword(passwordTextVal.value)
    val inpText = "${Constants.PASSWORD_STRENGTH} : $passStr"
    val strStrIndex = passStr.length
    val startIndex = inpText.length - strStrIndex
    val endIndex = inpText.length
    val colorTemp = Utilities.calculatePasswordStrengthColor(passwordTextVal.value)
    anotatedStringPasswordStrength = Utilities.createSpannableTextColor(
        inpText,
        startIndex,
        endIndex,
        colorTemp
    )

    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = anotatedStringPasswordStrength!!,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 19.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


@Composable
fun RowComponentImageButtons() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        ImageButtonTheme(isThemeIconVisible.value)
        ImageButtonAbout()
    }
}


fun initValues() {
    try {
        for (i in 0..25)
            capital_letters_array[i] = (i + 65).toChar().toString()

        for (i in 0..25)
            small_letters_array[i] = (i + 97).toChar().toString()

        for (i in 0..9)
            numbers_array[i] = i.toString()

        symbols_array = Constants.SYMBOLS_LIST

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun generateRandomPassword() {
    try {
        if (!mCheckedStateCapitalLetters.value && !mCheckedStateSmallLetters.value && !mCheckedStateSymbol.value && !mCheckedStateNumbers.value) {
            //No choices selected
            showSnackBarCoroutine(
                Constants.SELECT_ATLEAST_ONE_CONSTRAINT_TO_GENERATE_STRONGER_PASSWORD,
            )
            return
        }
        if (passwordLengthVal.value < 1) {
            showSnackBarCoroutine(Constants.LENGTH_SHOULD_NOT_BE_EMPTY)
            return
        }
        val currentVal = passwordLengthVal.value
        if (currentVal < Constants.MIN_LENGTH) {
            showSnackBarCoroutine(
                Constants.PASSWORD_MIN_LENGTH_CONSTRAINT
            )
            return
        } else if (currentVal > Constants.MAX_LENGTH) {
            showSnackBarCoroutine(
                Constants.PASSWORD_MAX_LENGTH_CONSTRAINT
            )
            return
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    try {
        val selectedChoices = booleanArrayOf(
            mCheckedStateCapitalLetters.value,
            mCheckedStateSmallLetters.value,
            mCheckedStateNumbers.value,
            mCheckedStateSymbol.value
        )
        val maxAvailableChoices = 3
        val elements =
            arrayOf(capital_letters_array, small_letters_array, numbers_array, symbols_array)
        val newPassword = StringBuilder()
        val passwordLength = passwordLengthVal.value
        for (i in 0 until passwordLength) {
            var randomChoice: Int
            do {
                val rnd = (0..maxAvailableChoices).random()
                randomChoice = rnd
                if (randomChoice >= selectedChoices.size) randomChoice = selectedChoices.size - 1
            } while (!selectedChoices[randomChoice])

            val tSize = elements[randomChoice].size - 1
            val rnd1 = (0..tSize).random()
            var randomCharIndex = rnd1
            if (randomCharIndex >= elements[randomChoice].size) randomCharIndex =
                elements[randomChoice].size - 1
            newPassword.append(elements[randomChoice][randomCharIndex])
        }
        passwordTextVal.value = newPassword.toString()
        showSnackBarCoroutine(Constants.RANDOM_PASSWORD_GENERATED)
        val tempText = Utilities.calculateStrengthOfPassword(newPassword.toString())
        mPasswordStrength.value = "${Constants.PASSWORD_STRENGTH} : $tempText"

        settingsPrefs.updateKeyValueToDataStoreString(
            Constants.prefsPasswordSavedOld,
            passwordTextVal.value
        )
        settingsPrefs.updateKeyValueToDataStoreString(
            Constants.prefsPasswordSavedOldStr,
            mPasswordStrength.value
        )
        settingsPrefs.updateKeyValueToDataStoreInt(
            Constants.prefsPasswordLength,
            passwordLengthVal.value
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun showSnackBarCoroutine(strVal: String) {
    CoroutineScope(Dispatchers.Default).launch {
        showSnackBarVal.value = true
        snackBarMessageVal.value = strVal
        delay(2500)
        // Update data on the main thread
        withContext(Dispatchers.Main) {
            showSnackBarVal.value = false
            snackBarMessageVal.value = Constants.SNACKBARMESSAGE_VAL_EMPTY
        }
    }
}
