package com.example.compose_mvi.core.utils

import android.annotation.SuppressLint
import android.util.Log
import org.jetbrains.annotations.NotNull
import timber.log.Timber

object TimberLogger {
    fun init(isDebug: Boolean) {
        if (isDebug) {
            Timber.plant(object :Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return String.format(
                        "Class: %s: | Line: %s, | Method: %s",
                        super.createStackElementTag(element),
                        element.lineNumber,
                        element.methodName
                    )
                }

                @SuppressLint("TimberArgCount")
                override fun d(message: String?, vararg args: Any?) {
                    if (message?.length == 0) {
                        super.d("EMPTY_STRING", *args)
                    } else {
                        super.d(message, *args)
                    }
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}

class ReleaseTree: @NotNull Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN) {
            // Crashlytics으로 보내는 로직 넣기

        }
    }
}