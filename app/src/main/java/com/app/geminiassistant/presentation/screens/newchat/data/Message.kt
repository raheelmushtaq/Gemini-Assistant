package com.app.geminiassistant.presentation.screens.newchat.data

import android.graphics.Bitmap

data class Message(
    val id: MessageType = MessageType.User,
    val images: List<Bitmap?> = emptyList(),
    val text: String = ""
)

enum class MessageType {
    User,
    Gemini,
    Error
}