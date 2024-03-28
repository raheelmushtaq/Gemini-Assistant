package com.app.geminiassistant.presentation.components.scaffold

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.app.geminiassistant.presentation.components.appheader.AppHeader
import com.app.geminiassistant.presentation.utils.ScreenRoutes

@Composable
fun BaseContainer(
    navController: NavController,
    title: String = "",
    showHeader: Boolean = false,
    showBackButton: Boolean = false,
    showFloatingActionButton: Boolean = false,
    content: @Composable () -> Unit

) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.pointerInput(key1 = true) {
            // when user presses our side of the ui then close the keyboard
            detectTapGestures(onTap = { focusManager.clearFocus() })
        },
        topBar = {
            if (showHeader) {
                AppHeader(
                    showBackButton = showBackButton,
                    navController = navController,
                    title = title,
                )
            }
        },
        floatingActionButton = {
            // added a floating action button
            if (showFloatingActionButton) {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {
                        // on clicking opening he new screen
                        navController.navigate(ScreenRoutes.NewChatScreen.route)
                    },
                    containerColor = Color.White,
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                }
            }
        },
    ) { defaultPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(defaultPadding)
        ) {
            content()
        }


    }
}