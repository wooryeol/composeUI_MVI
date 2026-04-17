package com.example.compose_mvi.di

import com.example.compose_mvi.core.network.OkHttpProvider
import com.example.compose_mvi.core.network.RetrofitProvider
import com.example.compose_mvi.core.utils.LogTag
import com.example.compose_mvi.core.utils.logD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger { msg -> logD(LogTag.NETWORK, msg) }
        return HttpLoggingInterceptor(logger).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpProvider.provideClient(logging)
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return RetrofitProvider.provideRetrofit(client)
    }
}