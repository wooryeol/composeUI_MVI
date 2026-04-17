package com.example.compose_mvi.feature.login.domain.usecase

import com.example.compose_mvi.core.utils.LogTag
import com.example.compose_mvi.core.utils.logD
import com.example.compose_mvi.core.utils.toSha512
import com.example.compose_mvi.feature.login.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(id: String, pw: String): String {
        logD(LogTag.AUTH, "로그인 UseCase 시작")

        val encryptedPw = pw.toSha512()

        logD(LogTag.AUTH, "비밀번호 SHA512 암호화 완료")

        val token = repository.login(id, encryptedPw)

        logD(LogTag.AUTH, "로그인 UseCase 완료")

        return token
    }
}