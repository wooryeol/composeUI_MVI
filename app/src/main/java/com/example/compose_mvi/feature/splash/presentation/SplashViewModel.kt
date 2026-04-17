package com.example.compose_mvi.feature.splash.presentation

import androidx.lifecycle.viewModelScope
import com.example.compose_mvi.core.base.MviViewModel
import com.example.compose_mvi.core.utils.LogTag
import com.example.compose_mvi.core.utils.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    // TODO: 나중에 DataStore / TokenManager 들어감
): MviViewModel<
        SplashContract.Intent,
        SplashContract.State,
        SplashContract.Effect
        >() {
    override fun handleIntent(intent: SplashContract.Intent) {
        when (intent) {
            SplashContract.Intent.CheckLogin -> checkLogin()
        }
    }

    override fun createInitialState(): SplashContract.State {
        return SplashContract.State()
    }

    private fun checkLogin() {
        viewModelScope.launch {
            logD(LogTag.AUTH, "로그인 여부 확인 시작")

            delay(1500) // splash delay

            // TODO: 실제 로그인 여부 판단(토큰 체크)
            val isLogin = false

            if(isLogin) {
                logD(LogTag.AUTH, "자동 로그인 성공 → 메뉴 이동")

                setEffect {
                    SplashContract.Effect.NavigationToMenu
                }

            } else {
                logD(LogTag.AUTH, "로그인 필요 → 로그인 화면 이동")

                setEffect {
                    SplashContract.Effect.NavigationToLogin
                }

            }
        }
    }
}