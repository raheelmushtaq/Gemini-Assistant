package com.app.geminiassistant.presentation.components.edittext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/*
* AppEditText is composable which is used in the the application.this component is based on InputField.
* this component take different option to show a InputField
*  textFieldValue to be, which is the value we have saved on when user types the text
* hint is used to show the placeholder in case if their is no textFieldValue
* onValueChange callback is used when user has listen the change on text field i.e. when user is typing. t
* read only is to show that this value is only for read and it cannot be editable
* onDone callback is called when user presses the search or done button on the keyboard
* */
@Composable
fun AppEditTextField(
    textFieldValue: String,
    hint: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = true,
    onDone: () -> Unit = {},
) {
//    show outlined TextField for user
    OutlinedTextField(
            value = textFieldValue,
            label = { Text(text = hint) },
            singleLine = true,
            readOnly = readOnly,
            shape = RoundedCornerShape(30),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onDone() }),
        )
}

/*
* AppDescriptionTextField is composable which is used in the the application.this component is based on InputField.
* this component take different option to show a InputField
*  textFieldValue to be, which is the value we have saved on when user types the text
* hint is used to show the placeholder in case if their is no textFieldValue
* onValueChange callback is used when user has listen the change on text field i.e. when user is typing. t
* read only is to show that this value is only for read and it cannot be editable
* onDone callback is called when user presses the search or done button on the keyboard
* */
@Composable
fun AppDescriptionTextField(
    textFieldValue: String,
    hint: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = true,
    onDone: () -> Unit = {},
) {
    //    show outlined TextField for user with a bigger height
    OutlinedTextField(
            value = textFieldValue,
            label = { Text(text = hint) },
            singleLine = false,
            readOnly = readOnly,
            shape = RoundedCornerShape(10),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onDone() }),
        )
}