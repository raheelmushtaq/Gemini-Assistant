package com.app.geminiassistant.presentation.screens.chats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.geminiassistant.R
import com.app.geminiassistant.presentation.components.chat.ChatCard
import com.app.geminiassistant.presentation.components.scaffold.BaseContainer
import com.app.geminiassistant.presentation.components.textfields.LargeText
import com.app.geminiassistant.presentation.screens.chats.viewmodel.ChatViewModel
import com.app.geminiassistant.presentation.utils.ScreenRoutes

@Composable
fun ChatScreen(navHostController: NavHostController, viewModel: ChatViewModel = hiltViewModel()) {


    val datastate = viewModel.dataState

    BaseContainer(
        navController = navHostController,
        showHeader = true,
        title = stringResource(id = R.string.app_name),
        showFloatingActionButton = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            if (datastate.value.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp)
                ) {

                    LargeText(
                        text = stringResource(id = R.string.no_chats_found),
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                LazyColumn {
                    items(datastate.value.toList(), key = { it.date }) { item ->
                        ChatCard(item = item) {
                            navHostController.navigate(ScreenRoutes.ChatDetailScreen.route + "?id=${item.date}")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}