package com.example.compose_mvi.feature.login.presentation

import com.example.compose_mvi.core.base.MviEffect
import com.example.compose_mvi.core.base.MviIntent
import com.example.compose_mvi.core.base.MviState

object LoginContract {

    // Intent
    sealed class Intent: MviIntent {
        data class Login(val id: String, val pw: String): Intent()
        data class OnIdChanged(val id: String): Intent()
        data class OnPwChanged(val pw: String): Intent()
    }

    // State
    data class State (
        val id: String = "",
        val pw: String = "",
        val isLoading: Boolean = false
    ): MviState

    // Effect
    sealed class Effect: MviEffect {
        object NavigationToMenu: Effect()
        data class ShowToast(val msg: String): Effect()
    }
}