package com.example.compose_mvi.feature.splash.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    // 최초 실행
    LaunchedEffect(Unit) {
        viewModel.sendIntent(SplashContract.Intent.CheckLogin)
    }

    // Effect 처리
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SplashContract.Effect.NavigationToMenu -> {
                    navController.navigate("menu") {
                        popUpTo("splash") {inclusive = true}
                    }
                }

                is SplashContract.Effect.NavigationToLogin -> {
                    navController.navigate("login") {
                        popUpTo("splash") {inclusive = true}
                    }
                }
            }
        }
    }

    // UI
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Splash Loading...")
    }
}