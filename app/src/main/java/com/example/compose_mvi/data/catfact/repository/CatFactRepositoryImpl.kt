package com.example.compose_mvi.data.catfact.repository

import com.example.compose_mvi.data.catfact.remote.CatFactApi

class CatFactRepositoryImpl(
    private val api: CatFactApi
): CatFactRepository {
    override suspend fun getFactCatData(): String {
        return api.getFactCat().fact
    }
}