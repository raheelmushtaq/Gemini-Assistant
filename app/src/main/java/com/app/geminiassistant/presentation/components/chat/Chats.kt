package com.app.geminiassistant.presentation.components.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.app.geminiassistant.presentation.screens.newchat.data.Message
import com.app.geminiassistant.ui.theme.Blue400
import com.app.geminiassistant.ui.theme.Grey1

@Composable
fun MessageUser(message: Message) {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Top
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = message.text, color = Blue400, modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topEnd = 0.dp,
                                bottomEnd = 25.dp,
                                bottomStart = 25.dp,
                                topStart = 25.dp
                            )
                        )
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(Grey1)
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
                Spacer(modifier = Modifier.size(5.dp))
                Row {
                    message.images.forEach {
                        it?.asImageBitmap()?.let { it1 ->
                            Image(
                                bitmap = it1,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(65.dp)
                                    .padding(5.dp)
                                    .clip(RoundedCornerShape(5.dp))
                            )
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.size(10.dp))
//                Image(
//                    painterResource(id = R.drawable.profile_image),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(35.dp)
//                        .clip(CircleShape)
//                )

        }
    }

}
