package com.example.compose_mvi.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<Intent: MviIntent, State: MviState, Effect: MviEffect>: ViewModel() {

    abstract fun createInitialState(): State

    private val _state = MutableStateFlow(createInitialState())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    abstract fun handleIntent(intent: Intent)

    protected fun setState(reducer: State.() -> State) {
        val newState = _state.value.reducer()
        _state.value = newState
    }

    protected fun setEffect(builder: () -> Effect) {
        viewModelScope.launch {
            _effect.emit(builder())
        }
    }
}