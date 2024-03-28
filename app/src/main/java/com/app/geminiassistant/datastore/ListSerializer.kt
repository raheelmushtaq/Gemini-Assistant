package com.app.geminiassistant.datastore

import com.app.geminiassistant.presentation.screens.chats.data.ChatMessage
import com.app.geminiassistant.presentation.screens.chats.data.Chats
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = PersistentList::class)
class SerializerPersistentChats(
    private val serializer: KSerializer<Chats>,
) : KSerializer<PersistentList<Chats>> {

    private class PersistentListDescriptor :
        SerialDescriptor by serialDescriptor<List<Chats>>() {
        override val serialName: String = "kotlinx.serialization.immutable.persistentList"
    }

    override val descriptor: SerialDescriptor = PersistentListDescriptor()

    override fun serialize(encoder: Encoder, value: PersistentList<Chats>) {
        return ListSerializer(serializer).serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): PersistentList<Chats> {
        return ListSerializer(serializer).deserialize(decoder).toPersistentList()
    }

}
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = PersistentList::class)
class SerializerPersistentChatMessage(
    private val serializer: KSerializer<ChatMessage>,
) : KSerializer<PersistentList<ChatMessage>> {

    private class PersistentListDescriptor :
        SerialDescriptor by serialDescriptor<List<ChatMessage>>() {
        override val serialName: String = "kotlinx.serialization.immutable.persistentList"
    }

    override val descriptor: SerialDescriptor = PersistentListDescriptor()

    override fun serialize(encoder: Encoder, value: PersistentList<ChatMessage>) {
        return ListSerializer(serializer).serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): PersistentList<ChatMessage> {
        return ListSerializer(serializer).deserialize(decoder).toPersistentList()
    }

}
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = PersistentList::class)
class SerializerPersistentString(
    private val serializer: KSerializer<String>,
) : KSerializer<PersistentList<String>> {

    private class PersistentListDescriptor :
        SerialDescriptor by serialDescriptor<List<String>>() {
        override val serialName: String = "kotlinx.serialization.immutable.persistentList"
    }

    override val descriptor: SerialDescriptor = PersistentListDescriptor()

    override fun serialize(encoder: Encoder, value: PersistentList<String>) {
        return ListSerializer(serializer).serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): PersistentList<String> {
        return ListSerializer(serializer).deserialize(decoder).toPersistentList()
    }

}