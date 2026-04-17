package com.example.compose_mvi.feature.login.data.datasource

import com.example.compose_mvi.core.utils.LogTag
import com.example.compose_mvi.core.utils.logD
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalDataSource @Inject constructor() {

    private var token: String? = null

    fun saveToken(token: String) {
        logD(LogTag.AUTH, "토큰 저장: $token")
        this.token = token
    }

    fun getToken(): String? {
        return token
    }

    fun clearToken() {
        logD(LogTag.AUTH, "토큰 삭제")
        token = null
    }
}