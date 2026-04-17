package com.example.compose_mvi.core.utils

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


/*
* 다음 인풋창으로 이동
* 없을 경우 함수 실행 시킬 수 있도록
*/
@Composable
fun CommonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    visualTransformation : PasswordVisualTransformation? = null,
    // 핵심
    imeAction: ImeAction = ImeAction.Next,
    nextFocusRequester: FocusRequester? = null,
    onDone: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { androidx.compose.material3.Text(label)},
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        visualTransformation = PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(
            onNext = {
                nextFocusRequester?.requestFocus()
            },
            onDone = {
                onDone?.invoke()
            }
        ),
        singleLine = true,
        modifier = modifier
    )
}
