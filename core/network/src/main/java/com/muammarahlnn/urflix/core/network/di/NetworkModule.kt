package com.muammarahlnn.urflix.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.muammarahlnn.urflix.core.network.BuildConfig
import com.muammarahlnn.urflix.core.network.api.MovieApi
import com.muammarahlnn.urflix.core.network.api.TvShowApi
import com.muammarahlnn.urflix.core.network.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file NetworkModule, 02/11/2023 11.11 by Muammar Ahlan Abimanyu
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesMovieApi(
        networkJson: Json,
        client: OkHttpClient,
    ): MovieApi = buildRetrofit(networkJson, client).create(MovieApi::class.java)

    @Provides
    @Singleton
    fun providesTvShowApi(
        networkJson: Json,
        client: OkHttpClient,
    ): TvShowApi = buildRetrofit(networkJson, client).create(TvShowApi::class.java)

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = buildOkHttpClient()
}

private fun buildOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().apply {
        addInterceptor(
            HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            }
        )
        addInterceptor(ApiKeyInterceptor(API_KEY))
        connectTimeout(120, TimeUnit.SECONDS)
        readTimeout(120, TimeUnit.SECONDS)
    }.build()

private fun buildRetrofit(
    networkJson: Json,
    client: OkHttpClient,
): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(
        networkJson.asConverterFactory(
            "application/json".toMediaType()
        )
    )
    .client(client)
    .build()

private const val BASE_URL = BuildConfig.BASE_URL
private const val API_KEY = BuildConfig.API_KEY