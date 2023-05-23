package com.jefferymoju.borutoapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jefferymoju.borutoapp.R
import com.jefferymoju.borutoapp.navigation.Screen
import com.jefferymoju.borutoapp.ui.theme.Purple500
import com.jefferymoju.borutoapp.ui.theme.Purple700
import com.jefferymoju.borutoapp.ui.theme.statusBarColor

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor
    SideEffect { // to change our app systembars
        systemUiController.setSystemBarsColor(
            color = systemBarColor
        )
    }

    //val to observe the onBoarding state from our viewModel
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    val degrees = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true){// this launch effect will trigger its content only the first time we call the screen
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted){
            navController.navigate(Screen.Home.route)
        }else{
            navController.navigate(Screen.Welcome.route)
        }
    }

    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees: Float){
    val modifier = if (isSystemInDarkTheme()) Modifier.background(Color.Black)
    else Modifier.background(Brush.verticalGradient(listOf(Purple700, Purple500)))


        Box(
            modifier = modifier
                .fillMaxSize(), // to fill the whole screen
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier.rotate(degrees = degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )
        }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(degrees = 0f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash(degrees = 0f)
}