package com.app.geminiassistant.presentation.screens.chatdetail.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.geminiassistant.datastore.DataStoreHandler
import com.app.geminiassistant.presentation.screens.chats.data.Chats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    private val dataStoreHandler: DataStoreHandler,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private var _dataState: MutableState<List<Chats>> = mutableStateOf(listOf())
    val dataState = _dataState

    private var _chatItem: MutableState<Chats> = mutableStateOf(Chats())
    val chatItem = _chatItem

    init {
        viewModelScope.launch {
            dataStoreHandler.getAppSettings().collectLatest { appSettings ->
                _dataState.value = appSettings.chats.toList()
            }
        }
        savedStateHandle.get<Long>("id")?.let { id ->
            if (id.toInt() != -1) {
                val task = getTaskById(id)
                updateTaskData(task)
            }
        }
    }

    private fun updateTaskData(chats: Chats?) {
        chats?.let { chat ->
            _chatItem.value = chats
        }
    }

    fun getTaskById(taskId: Long): Chats? {
        val filterTasks = dataState.value.filter { task -> task.date == taskId }
        return if (filterTasks.isNotEmpty()) {
            filterTasks[0]

        } else null
    }
}