package com.sujith.ui.feature_splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sujith.ui.R
import com.sujith.ui.navigation.CharacterList
import com.sujith.ui.navigation.Splash
import com.sujith.ui.theme.splashBg
import com.sujith.ui.utils.Lottie
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = splashBg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Lottie(
            rawFile = R.raw.splash_anim,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier.size(300.dp)
        )
        Text(
            text = stringResource(id = R.string.harry_potter),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 23.sp
        )
        LaunchedEffect(key1 = true) {
            delay(3000)
            navController.navigate(CharacterList) {
                popUpTo<Splash> {
                    inclusive = true
                }
            }
        }
    }
}