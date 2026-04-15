package com.example.compose_mvi.data.catfact.remote

import retrofit2.http.GET

interface CatFactApi {
    @GET("fact")
    suspend fun getFactCat(): CatFactResponse
}
data class CatFactResponse(
    val fact: String,
    val length: Int
)