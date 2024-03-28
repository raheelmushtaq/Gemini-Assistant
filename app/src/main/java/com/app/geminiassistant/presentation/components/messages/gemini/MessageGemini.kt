package com.app.geminiassistant.presentation.components.messages.gemini

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.geminiassistant.R
import com.app.geminiassistant.presentation.screens.newchat.data.Message
import com.app.geminiassistant.ui.theme.Blue400

@Composable
fun MessageGemini(message: Message) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top
        ) {


            Image(
                painter = painterResource(id = R.drawable.geminiai),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp, color = Blue400, shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = message.text, color = Color.White, modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topEnd = 25.dp,
                            bottomEnd = 25.dp,
                            bottomStart = 25.dp,
                            topStart = 0.dp
                        )
                    )
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(Blue400)
                    .padding(horizontal = 20.dp, vertical = 10.dp)

            )

        }
    }

}
