package com.example.compose_mvi.presentation.catFact

import com.example.compose_mvi.core.base.MviEffect
import com.example.compose_mvi.core.base.MviIntent
import com.example.compose_mvi.core.base.MviState

object CatFactContract {

    data class State (
        val isLoading: Boolean = false,
        val fact: String = "",
        val error: String? = null
    ): MviState

    sealed class Intent: MviIntent {
        object Load: Intent()
        object Refresh: Intent()
    }

    sealed class Effect: MviEffect {
        data class ShowToast(val msg: String) : Effect()
    }
}