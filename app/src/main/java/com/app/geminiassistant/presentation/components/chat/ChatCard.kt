package com.app.geminiassistant.presentation.components.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.geminiassistant.presentation.components.textfields.MediumText
import com.app.geminiassistant.presentation.screens.chats.data.Chats
import com.app.geminiassistant.utils.toDate

@Composable
fun ChatCard(
    modifier: Modifier = Modifier, item: Chats, onClick: (Chats) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick(item) },
        colors = CardDefaults.cardColors(
        )
    ) {
        // adding a column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row {
                //showing the title of the tasks
                MediumText(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // adding a row
            // adding a column to show date
            MediumText(text = item.date.toDate())

        }
    }
}