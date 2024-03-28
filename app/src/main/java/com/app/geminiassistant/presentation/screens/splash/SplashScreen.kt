package com.app.geminiassistant.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.geminiassistant.R
import com.app.geminiassistant.presentation.components.scaffold.BaseContainer
import com.app.geminiassistant.presentation.utils.ScreenRoutes
import com.app.geminiassistant.utils.addDelay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    LaunchedEffect(key1 = true) {
        addDelay(1500) {
            navHostController.navigate(ScreenRoutes.ChatsScreen.route) {
                launchSingleTop = true
                popUpTo(ScreenRoutes.SplashScreen.route) {
                    inclusive = true
                }

            }
        }
    }

    BaseContainer(navController = navHostController) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.geminiai),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                )

            }
        }
    }
}