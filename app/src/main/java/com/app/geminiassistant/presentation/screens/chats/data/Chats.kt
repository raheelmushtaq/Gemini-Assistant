package com.app.geminiassistant.presentation.screens.chats.data

import com.app.geminiassistant.datastore.SerializerPersistentChatMessage
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class Chats(
    var date: Long = 0,
    val title: String="",
    @Serializable(with = SerializerPersistentChatMessage::class)
    val message: PersistentList<ChatMessage> = persistentListOf()
)