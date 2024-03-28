package com.app.geminiassistant.datastore.model

import com.app.geminiassistant.datastore.SerializerPersistentChats
import com.app.geminiassistant.presentation.screens.chats.data.Chats
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val recordCount: Int = 0,
    @Serializable(with = SerializerPersistentChats::class)
    val chats: PersistentList<Chats> = persistentListOf(),
)