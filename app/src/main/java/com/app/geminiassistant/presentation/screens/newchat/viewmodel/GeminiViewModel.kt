package com.app.geminiassistant.presentation.screens.newchat.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.geminiassistant.BuildConfig
import com.app.geminiassistant.datastore.DataStoreHandler
import com.app.geminiassistant.presentation.screens.chats.data.Chats
import com.app.geminiassistant.presentation.screens.newchat.data.Message
import com.app.geminiassistant.presentation.screens.newchat.data.MessageType
import com.app.geminiassistant.utils.toChatMessage
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeminiViewModel @Inject constructor(private val dataStoreHandler: DataStoreHandler) :
    ViewModel() {

    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = BuildConfig.GEMINI_KEY,
        )
    }
    private val generativeModelForText by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.GEMINI_KEY,
        )
    }

    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val chat: MutableState<List<Message>> = mutableStateOf(listOf())

    val images: MutableState<ArrayList<Bitmap>> = mutableStateOf(arrayListOf())

    fun addImage(bitmap: Bitmap) {
        images.value.add(bitmap)
    }


    fun sendPrompt(prompt: String, images: List<Bitmap>) {
        val chunks = mutableListOf("")
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value = true
            chat.value += Message(
                images = images, text = prompt
            )

            val inputContent = if (images.size > 0) {
                content {
                    images.forEach { imageBitmap ->
                        image(imageBitmap)
                    }
                    text(prompt)
                }
            } else {
                content {
                    text(prompt)
                }
            }

            (if (images.size > 0) generativeModel else generativeModelForText).generateContentStream(
                inputContent
            ).catch {
                chat.value += Message(
                    id = MessageType.Error,
                    text = it.localizedMessage ?: "Unknown Error",
                )

                dataStoreHandler.addNewChat(
                    Chats(
                        System.currentTimeMillis(),
                        chat.value.get(0).text,
                        chat.value.toChatMessage().toPersistentList()
                    )
                )
            }.collect { chunk ->
                chunks += chunk.text.toString()
                val last = chat.value.last()
                if (last.id == MessageType.Gemini) {
                    chat.value = chat.value.filter { message ->
                        message != last
                    }
                }
                chat.value += Message(
                    id = MessageType.Gemini,
                    text = chunks.joinToString(""),
                )
            }

            dataStoreHandler.addNewChat(
                Chats(
                    System.currentTimeMillis(),
                    chat.value.get(0).text,
                    chat.value.toChatMessage().toPersistentList()
                )
            )

            chunks.clear()
            isLoading.value = false
        }
    }

    fun reset() {
        viewModelScope.launch {
            images.value.clear()
        }
    }

    fun removeImage(image: Bitmap) {
        images.value.remove(image)
    }
}