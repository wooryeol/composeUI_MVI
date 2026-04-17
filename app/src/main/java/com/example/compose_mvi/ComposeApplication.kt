package com.example.compose_mvi

import android.app.Application
import android.util.Log
import com.example.compose_mvi.core.utils.TimberLogger
import dagger.hilt.android.HiltAndroidApp
import org.jetbrains.annotations.NotNull
import timber.log.Timber

@HiltAndroidApp
class ComposeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 디버그 모드에서만 로그 출력
        TimberLogger.init(BuildConfig.DEBUG)
    }
}