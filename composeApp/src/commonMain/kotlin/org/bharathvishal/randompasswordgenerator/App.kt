package org.bharathvishal.randompasswordgenerator


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Snackbar
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bharathvishal.biometricauthentication.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import randompasswordgenerator.composeapp.generated.resources.Res
import randompasswordgenerator.composeapp.generated.resources.baseline_password_24

private var showSnackBarVal = mutableStateOf(false)
private var snackBarMessageVal = mutableStateOf("-")

@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        MainViewImplementation()
    }
}


@Composable
fun MainViewImplementation() {
    Column {
        TopAppBarMain()
        Box(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            CardViewMain()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomStart),
            ) {
                SnackBarViewComposable(showSnackBarVal.value, snackBarMessageVal.value)
            }
        }
    }
}

@Composable
fun TopAppBarMain() {
    TopAppBar(
        title = { Text("Random Password Generator") },
        backgroundColor = MaterialTheme.colors.primarySurface
    )
}

@Suppress("UNNECESSARY_SAFE_CALL")
@Composable
fun CardViewMain() {
    Column {
        Spacer(modifier = Modifier.padding(top = 6.dp))
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                ImageLogo()
                TextHeader()
                Divider(thickness = 0.5.dp)
                RowComponentButtons()
                Divider(thickness = 0.5.dp)
                RowComponentInCardSlider("Password Length")
                Divider(thickness = 0.5.dp)
                RowComponentInCardSwitch("Include capital letters (A ,B, C)")
                Divider(thickness = 0.5.dp)
                RowComponentInCardSwitch("Include small letters (a ,b, c)")
                Spacer(modifier = Modifier.padding(top = 6.dp))
                RowComponentInCardSwitch("Include numbers (1 ,2, 3)")
                Spacer(modifier = Modifier.padding(top = 6.dp))
                RowComponentInCardSwitch("Include symbols (@ ,!, %)")
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
        modifier = Modifier
            .requiredHeight(125.dp)
            .requiredWidth(125.dp)
            .padding(5.dp)
    )
}

//Biometric Authentication app name Text
@Composable
fun TextHeader() {
    Text(
        text = "Random Password Generator - KMM",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun SnackBarViewComposable(visibilityState: Boolean, message: String) {
    AnimatedVisibility(visible = visibilityState) {
        Snackbar(action = {}) {
            Text(text = message)
        }
    }
}


//Row component composable function for Biometric related info
@Composable
fun RowComponentInCardSlider(strDesc: String) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = strDesc,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.primary
        )

        Slider(
            value = sliderPosition.toFloat(),
            onValueChange = { sliderPosition = it.toInt().toFloat() },
            steps = 30,
            valueRange = 9f..30f
        )
        Text(text = sliderPosition.toInt().toString())
    }
}


@Composable
fun RowComponentInCardSwitch(strDesc: String) {
    val mCheckedState = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = strDesc,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.primary
        )

        Switch(checked = mCheckedState.value,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary,
                checkedTrackColor = Color.Gray,
                uncheckedThumbColor = Color.LightGray,
                uncheckedTrackColor = Color.LightGray,
            ),
            onCheckedChange = { mCheckedState.value = it })
    }
}


@Composable
fun RowComponentButtons() {
    val mCheckedState = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Generate Password",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.primary
        )

        Switch(checked = mCheckedState.value,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary,
                checkedTrackColor = Color.Gray,
                uncheckedThumbColor = Color.LightGray,
                uncheckedTrackColor = Color.LightGray,
            ),
            onCheckedChange = { mCheckedState.value = it })
    }
}


