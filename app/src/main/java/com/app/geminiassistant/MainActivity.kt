package com.app.geminiassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.geminiassistant.presentation.screens.chatdetail.ChatDetailScreen
import com.app.geminiassistant.presentation.screens.chats.ChatScreen
import com.app.geminiassistant.presentation.screens.newchat.NewChatScreen
import com.app.geminiassistant.presentation.screens.splash.SplashScreen
import com.app.geminiassistant.presentation.utils.ScreenRoutes
import com.app.geminiassistant.ui.theme.GeminiTestTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            GeminiTestTheme {

                val navHostController = rememberNavController()

                NavHost(navController = navHostController,
                    startDestination = ScreenRoutes.SplashScreen.route,
                    enterTransition = {
                        fadeIn(
                            animationSpec = tween(
                                300, easing = LinearEasing
                            )
                        ) + slideIntoContainer(
                            animationSpec = tween(
                                300, easing = EaseIn
                            ), towards = AnimatedContentTransitionScope.SlideDirection.Start
                        )
                    },
                    exitTransition = {

                        fadeOut(
                            animationSpec = tween(
                                300, easing = LinearEasing
                            )
                        ) + slideOutOfContainer(
                            animationSpec = tween(
                                300, easing = EaseOut
                            ), towards = AnimatedContentTransitionScope.SlideDirection.End
                        )
                    }) {
                    composable(ScreenRoutes.SplashScreen.route) {
                        SplashScreen(navHostController = navHostController)

                    }
                    composable(ScreenRoutes.ChatsScreen.route) {
                        ChatScreen(navHostController = navHostController)

                    }
                    composable(
                        ScreenRoutes.ChatDetailScreen.route+"?id={id}", arguments = listOf(navArgument("id") {
                            type = NavType.LongType
                            defaultValue = -1

                        })
                    ) {
                        ChatDetailScreen(navHostController = navHostController)

                    }
                    composable(ScreenRoutes.NewChatScreen.route) {
                        NewChatScreen(navHostController = navHostController)
                    }
                }
            }
        }
    }
}