package com.app.geminiassistant.presentation.screens.newchat

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.geminiassistant.R
import com.app.geminiassistant.presentation.components.messages.error.MessageError
import com.app.geminiassistant.presentation.components.messages.gemini.MessageGemini
import com.app.geminiassistant.presentation.components.messages.user.MessageUser
import com.app.geminiassistant.presentation.components.scaffold.BaseContainer
import com.app.geminiassistant.presentation.components.textfields.RegularText
import com.app.geminiassistant.presentation.screens.newchat.data.MessageType
import com.app.geminiassistant.presentation.screens.newchat.viewmodel.GeminiViewModel
import com.app.geminiassistant.ui.theme.Grey1
import com.app.geminiassistant.ui.theme.Grey2
import com.app.geminiassistant.utils.uriToBitmap

@Composable
fun NewChatScreen(
    navHostController: NavHostController, viewModel: GeminiViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val pickPhotoLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris: List<Uri>? ->
            uris?.let {
                viewModel.reset()
                it.forEach { selectedImage ->
                    context.contentResolver.uriToBitmap(selectedImage)?.let { bitmap ->
                        viewModel.addImage(bitmap)
                    }
                }
            }
        }

    BaseContainer(
        navController = navHostController,
        showHeader = true,
        title = stringResource(id = R.string.new_chat),
        showFloatingActionButton = false,
        showBackButton = true
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {


            val chat = viewModel.chat.value
            val loading = viewModel.isLoading.value
            val unSentImages = viewModel.images
            var textState by remember { mutableStateOf(TextFieldValue("")) }

            val lazyColumnListState = rememberLazyListState()

            LaunchedEffect(chat.size) {
                lazyColumnListState.animateScrollToItem(lazyColumnListState.layoutInfo.totalItemsCount)
            }
            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ) { padding ->
                Column(
                    Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal
                            )
                        )
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.geminiai),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(40.dp),
                        contentDescription = null
                    )

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        state = lazyColumnListState
                    ) {
                        itemsIndexed(chat) { _, message ->
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
                    Column(
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(
                                    topStart = 10.dp, topEnd = 10.dp
                                ), color = Grey2
                            )
                            .padding(vertical = 20.dp)
                    ) {
                        AnimatedVisibility(visible = unSentImages.value.isEmpty()) {
                            LazyRow {
                                items(unSentImages.value) {
                                    Box {
                                        Image(
                                            bitmap = it.asImageBitmap(),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(65.dp)
                                                .padding(horizontal = 5.dp)
                                                .clip(RoundedCornerShape(5.dp))
                                        )
                                        Icon(imageVector = Icons.Default.Clear,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .size(15.dp)
                                                .clip(CircleShape)
                                                .clickable {
                                                    viewModel.removeImage(it)
                                                }
                                                .background(Color.Red))
                                    }

                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        shape = RoundedCornerShape(200.dp), color = Grey1
                                    )
                                    .padding(16.dp)
                            ) {
                                BasicTextField(modifier = Modifier.fillMaxWidth(),
                                    value = textState,
                                    onValueChange = {
                                        textState = it
                                    })
                                if (textState.text.isEmpty()) Text(text = "Message GeminiAI ...")

                            }
                            Spacer(modifier = Modifier.size(10.dp))
                            Button(onClick = {
                                val pickPhotoIntent = PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                                pickPhotoLauncher.launch(pickPhotoIntent)
                            }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            }

                            Spacer(modifier = Modifier.size(10.dp))

                            if (loading) {
                                CircularProgressIndicator()
                            } else {
                                Button(
                                    enabled = textState.text.isNotEmpty() ||
                                            unSentImages.value.isNotEmpty() && textState.text.isNotEmpty(),
                                    onClick = {
                                        val images = unSentImages.value.map {
                                            it
                                        }
                                        viewModel.sendPrompt(textState.text, images)
                                        textState = TextFieldValue("")
                                        viewModel.reset()

                                    }) {
                                    Icon(
                                        imageVector = Icons.Default.Send,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                        RegularText(
                            text = "Enter a prompt and select images",
//                            fontSize = 12.sp,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 20.dp)
                        )

                    }

                }
            }
        }
    }
}