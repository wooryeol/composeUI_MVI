package com.example.compose_mvi.data.catfact.mapper

import com.example.compose_mvi.data.catfact.remote.CatFactResponse

fun CatFactResponse.toUiModel(): String {
    return fact
}