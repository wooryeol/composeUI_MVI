package com.example.compose_mvi.feature.login.presentation

import androidx.lifecycle.viewModelScope
import com.example.compose_mvi.core.base.MviViewModel
import com.example.compose_mvi.core.utils.LogTag
import com.example.compose_mvi.core.utils.logD
import com.example.compose_mvi.core.utils.logE
import com.example.compose_mvi.feature.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): MviViewModel<
        LoginContract.Intent,
        LoginContract.State,
        LoginContract.Effect
        >() {
    override fun handleIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.Login -> login(intent.id, intent.pw)

            is LoginContract.Intent.OnIdChanged -> {
                setState { copy(id = intent.id) }
            }
            is LoginContract.Intent.OnPwChanged -> {
                setState { copy(pw = intent.pw)}
            }
        }
    }

    override fun createInitialState(): LoginContract.State {
        return LoginContract.State()
    }

    private fun login(id: String, pw: String) {
        viewModelScope.launch {
            logD(LogTag.AUTH, "로그인 버튼 클릭")

            setState { copy(isLoading = true) }

            try {
                loginUseCase(id, pw)

                logD(LogTag.AUTH, "로그인 성공 -> 메뉴 이동")

                setState { copy(isLoading = false) }

                setEffect {
                    LoginContract.Effect.NavigationToMenu
                }

            } catch (e: Exception) {
                logE(LogTag.AUTH, "로그인 실패", e)

                setState { copy(isLoading = false)}

                setEffect {
                    LoginContract.Effect.ShowToast("로그인 실패")
                }
            }
        }
    }
}