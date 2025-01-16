package org.bharathvishal.randompasswordgenerator


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemGestures
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharathvishal.biometricauthentication.theme.AppTheme
import kotlinx.coroutines.launch
import org.bharathvishal.randompasswordgenerator.Constants.Constants
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import randompasswordgenerator.composeapp.generated.resources.Res
import randompasswordgenerator.composeapp.generated.resources.baseline_password_24

private var showSnackBarVal = mutableStateOf(false)
private var snackBarMessageVal = mutableStateOf("-")
private var passwordTextVal = mutableStateOf("ABCDEFGHIJK")
private var passwordLengthVal = mutableStateOf(9)
val mCheckedStateCapitalLetters = mutableStateOf(false)
val mCheckedStateSmallLetters = mutableStateOf(false)
val mCheckedStateNumbers = mutableStateOf(false)
val mCheckedStateSymbol = mutableStateOf(false)

@Composable
@Preview
fun App(
    darkTheme: Boolean, dynamicColor: Boolean
) {
    AppTheme(
        darkTheme = darkTheme, dynamicColor = dynamicColor
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            MainViewImplementation()
        }
    }
}


//Adds scroll view to compos
@Composable
fun MainViewImplementation() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(topBar = { TopAppBarMain() },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
        ) {
            Box(
                modifier = Modifier.padding(1.dp).fillMaxWidth().fillMaxHeight()
            ) {
                CardViewMain()
                Box(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                        .align(Alignment.BottomStart)
                ) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Snackbar Test"
                        )
                    }
                    //SnackBarViewComposable(showSnackBarVal.value, snackBarMessageVal.value)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMain() {
    TopAppBar(
        title = { Text("Random Password Generator - KMM") },
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Suppress("UNNECESSARY_SAFE_CALL")
@Composable
fun CardViewMain() {
    Column(
    ) {
        Spacer(modifier = Modifier.padding(top = 6.dp))
        Card(
            modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            colors = CardDefaults.cardColors(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth().wrapContentHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ImageLogo()
                TextHeader()
                Divider(thickness = 0.5.dp)
                RowComponentPasswordLabel(passwordTextVal.value)
                RowComponentButtons()
                Divider(thickness = 0.5.dp)
                RowComponentInCardSlider(Constants.PASSWORD_LENGTH)
                Divider(thickness = 0.5.dp)
                RowComponentInCardSwitch(Constants.INCLUDE_CAPITAL_LETTERS)
                Divider(thickness = 0.5.dp)
                RowComponentInCardSwitch(Constants.INCLUDE_SMALL_LETTERS)
                Spacer(modifier = Modifier.padding(top = 6.dp))
                RowComponentInCardSwitch(Constants.INCLUDE_NUMBERS)
                Spacer(modifier = Modifier.padding(top = 6.dp))
                RowComponentInCardSwitch(Constants.INCLUDE_SYMBOLS)
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
        modifier = Modifier.requiredHeight(125.dp).requiredWidth(125.dp).padding(5.dp)
    )
}

//Biometric Authentication app name Text
@Composable
fun TextHeader() {
    Text(
        text = "Random Password Generator",
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(5.dp).fillMaxWidth(),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun SnackBarViewComposable(visibilityState: Boolean, message: String) {
    AnimatedVisibility(visible = visibilityState) {
        Snackbar(
            action = {}
        ) {
            Text(text = message)
        }
    }
}


//Row component composable function for Biometric related info
@Composable
fun RowComponentInCardSlider(strDesc: String) {
    var sliderPosition by remember { mutableFloatStateOf(9f) }

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
            value = sliderPosition.toFloat(), onValueChange = {
                sliderPosition = it.toInt().toFloat()
                passwordLengthVal.value = it.toInt()
            }, steps = 30, valueRange = 9f..30f
        )
        Text(text = sliderPosition.toInt().toString())
    }
}


@Composable
fun RowComponentInCardSwitch(strDesc: String) {
    val mCheckedState = remember { mutableStateOf(false) }

    when (strDesc) {
        Constants.INCLUDE_CAPITAL_LETTERS -> mCheckedStateCapitalLetters.value = mCheckedState.value
        Constants.INCLUDE_SMALL_LETTERS -> mCheckedStateSmallLetters.value = mCheckedState.value
        Constants.INCLUDE_NUMBERS -> mCheckedStateNumbers.value = mCheckedState.value
        Constants.INCLUDE_SYMBOLS -> mCheckedStateSymbol.value = mCheckedState.value
    }

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

        Switch(checked = mCheckedState.value, onCheckedChange = { mCheckedState.value = it })
    }
}


@Composable
fun RowComponentButtons() {
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1F),
            onClick = {
                showSnackBarVal.value = true
                snackBarMessageVal.value = "Generated Random password"
            },
            contentPadding = PaddingValues(
                start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp
            ),
        ) {
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Generate Password")
        }

        OutlinedButton(
            modifier = Modifier.weight(1F),
            onClick = {
                showSnackBarVal.value = true
                snackBarMessageVal.value = "Copied Password"
            },
            contentPadding = PaddingValues(
                start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp
            ),
        ) {
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Copy Password")
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
            fontSize = 39.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


