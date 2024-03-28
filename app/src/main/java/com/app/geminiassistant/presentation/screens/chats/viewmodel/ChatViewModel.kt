package com.app.geminiassistant.presentation.screens.chats.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.geminiassistant.datastore.DataStoreHandler
import com.app.geminiassistant.presentation.screens.chats.data.Chats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val dataStoreHandler: DataStoreHandler) :
    ViewModel() {

    private var _dataState: MutableState<List<Chats>> = mutableStateOf(listOf())
    val dataState = _dataState

    init {
        viewModelScope.launch {
            dataStoreHandler.getAppSettings().collectLatest { appSettings ->
                _dataState.value = appSettings.chats.toList()
            }
        }
    }
}