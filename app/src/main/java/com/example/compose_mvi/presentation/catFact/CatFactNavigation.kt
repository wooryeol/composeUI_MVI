package com.example.compose_mvi.presentation.catFact

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.catFactScreen() {
    composable("catFact") {
        CatFactScreen()
    }
}