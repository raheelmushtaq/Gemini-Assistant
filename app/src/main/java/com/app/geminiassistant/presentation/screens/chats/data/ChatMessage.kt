package com.app.geminiassistant.presentation.screens.chats.data

import com.app.geminiassistant.datastore.SerializerPersistentString
import com.app.geminiassistant.presentation.screens.newchat.data.MessageType
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val id: MessageType = MessageType.User,
    @Serializable(with = SerializerPersistentString::class)
    val images: PersistentList<String> = persistentListOf(),
    val text: String = ""
)
