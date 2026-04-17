package com.example.compose_mvi.core.utils

import timber.log.Timber

object LogTag {
    const val NETWORK = "WMS_NETWORK"
    const val DB = "WMS_DB"
    const val UI = "WMS_UI"
    const val USECASE = "WMS_USECASE"
    const val SYNC = "WMS_SYNC"
    const val SCAN = "WMS_SCAN"
    const val AUTH = "WMS_AUTH"
    const val ERROR = "WMS_ERROR"
}

fun logD(tag: String, message: String) {
    Timber.tag(tag).d(message)
}

fun logE(tag: String, message: String, throwable: Throwable? = null) {
    Timber.tag(tag).e(throwable, message)
}

fun logI(tag: String, message: String) {
    Timber.tag(tag).i(message)
}