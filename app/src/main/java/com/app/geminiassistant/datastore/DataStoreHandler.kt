package com.app.geminiassistant.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.app.geminiassistant.presentation.screens.chats.data.Chats
import com.app.geminiassistant.datastore.model.AppSettings
import kotlinx.collections.immutable.mutate
import javax.inject.Inject

val Context.dataStore: DataStore<AppSettings> by dataStore(
    fileName = "gemini_chat_datastore.json", serializer = AppSettingsSerializer()
)
class DataStoreHandler @Inject constructor(private val context: Context) {

    fun getAppSettings() = context.dataStore.data


    suspend fun addNewChat(task: Chats) {

        context.dataStore.updateData { appSettings ->
            appSettings.copy(
                chats = appSettings.chats.mutate { list ->
                    list.add(task)
                }, recordCount = appSettings.recordCount + 1
            )
        }
    }
    suspend fun deleteTasks(task: Chats) {
        context.dataStore.updateData { appSettings ->
            appSettings.copy(chats = appSettings.chats.mutate { list ->
                val index = list.indexOfFirst { item -> item.date == task.date }
                if (index != -1) list.removeAt(index)
            })
        }
    }
}