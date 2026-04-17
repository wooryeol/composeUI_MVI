package com.example.compose_mvi.feature.login.domain.repository

interface AuthRepository {
    suspend fun login(id: String, pw: String): String
}