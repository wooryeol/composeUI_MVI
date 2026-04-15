package com.example.compose_mvi.data.catfact.repository

interface CatFactRepository {
    suspend fun getFactCatData(): String
}