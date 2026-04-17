package com.example.compose_mvi.feature.splash.presentation

import com.example.compose_mvi.core.base.MviEffect
import com.example.compose_mvi.core.base.MviIntent
import com.example.compose_mvi.core.base.MviState

object SplashContract {

    // Intent
    sealed class Intent: MviIntent {
        object CheckLogin: Intent()
    }

    // State
    data class State(
        val isLoading: Boolean = true
    ): MviState

    // Effect
    sealed class Effect: MviEffect {
        object NavigationToLogin: Effect()
        object NavigationToMenu: Effect()
    }
}