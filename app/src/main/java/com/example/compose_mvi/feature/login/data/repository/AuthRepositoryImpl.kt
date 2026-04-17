package com.example.compose_mvi.feature.login.data.repository

import com.example.compose_mvi.core.utils.LogTag
import com.example.compose_mvi.core.utils.logD
import com.example.compose_mvi.feature.login.data.datasource.AuthLocalDataSource
import com.example.compose_mvi.feature.login.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: AuthLocalDataSource
): AuthRepository {

    override suspend fun login(id: String, pw: String): String {
        logD(LogTag.AUTH, "로그인 시도: $id")

        // TODO: 실제 API 호출
        val fakeToken = "token_123456"

        logD(LogTag.AUTH, "로그인 성공, 토큰 발급")

        localDataSource.saveToken(fakeToken)

        return fakeToken
    }
}