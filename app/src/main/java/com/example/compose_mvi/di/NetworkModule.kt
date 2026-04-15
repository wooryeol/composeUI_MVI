package com.example.compose_mvi.di

import com.example.compose_mvi.data.catfact.remote.CatFactApi
import com.example.compose_mvi.data.catfact.repository.CatFactRepository
import com.example.compose_mvi.data.catfact.repository.CatFactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // 고양이에 대한 흥미로운 사실 api
    private val BASE_URL = "https://catfact.ninja/"
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCatFactApi(retrofit: Retrofit): CatFactApi {
        return retrofit.create(CatFactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCatFactRepository(api: CatFactApi): CatFactRepository {
        return CatFactRepositoryImpl(api)
    }
}