package com.app.geminiassistant.presentation.screens.chatdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.geminiassistant.R
import com.app.geminiassistant.presentation.components.messages.error.MessageError
import com.app.geminiassistant.presentation.components.messages.gemini.MessageGemini
import com.app.geminiassistant.presentation.components.messages.user.MessageUser
import com.app.geminiassistant.presentation.components.scaffold.BaseContainer
import com.app.geminiassistant.presentation.screens.chatdetail.viewmodel.ChatDetailViewModel
import com.app.geminiassistant.presentation.screens.newchat.data.MessageType
import com.app.geminiassistant.utils.toMessage

@Composable
fun ChatDetailScreen(
    navHostController: NavHostController, viewModel: ChatDetailViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val chatData = viewModel.chatItem

    BaseContainer(
        navController = navHostController,
        showHeader = true,
        title = stringResource(id = R.string.detail),
        showFloatingActionButton = false,
        showBackButton = true
    ) {
        Box(modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp)) {


            Column(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                ) {
                    itemsIndexed(chatData.value.message.toList().toMessage()) { _, message ->
                        when (message.id) {
                            MessageType.User -> {
                                MessageUser(message)
                            }

                            MessageType.Gemini -> {
                                MessageGemini(message)
                            }

                            else -> {
                                MessageError(message)
                            }
                        }
                    }
                }
            }
        }
    }
}