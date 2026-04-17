package com.example.compose_mvi.core.utils

import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.MessageDigest


// 비밀번호 SHA512 암호화
fun String.toSha512(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-512")
    val digest = md.digest(bytes)

    return digest.joinToString("") { "%02x".format(it) }
        .uppercase()
}

fun Modifier.singleClickable(
    interval: Long = 1000L,
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {

    var isClickable by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    clickable(enabled = enabled && isClickable) {
            isClickable = false
            onClick()

            // 일정 시간 후 다시 활성화
            scope.launch {
                delay(interval)
                isClickable = true
            }
    }
}