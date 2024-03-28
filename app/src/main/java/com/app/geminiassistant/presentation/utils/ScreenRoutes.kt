package com.app.geminiassistant.presentation.utils

/**
 * this class is used for creating the routes of the screen. they are used for routing screen
 */
sealed class ScreenRoutes(val route: String) {
    data object SplashScreen : ScreenRoutes("Splash")
    data object ChatsScreen : ScreenRoutes("ChatList")
    data object ChatDetailScreen : ScreenRoutes("ChatDetail")
    data object NewChatScreen: ScreenRoutes("NewChat")
}