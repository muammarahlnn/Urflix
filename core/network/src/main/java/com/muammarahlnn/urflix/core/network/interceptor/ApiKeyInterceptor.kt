package com.muammarahlnn.urflix.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ApiKeyInterceptor, 02/11/2023 11.17 by Muammar Ahlan Abimanyu
 */
class ApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
        val interceptedUrl = url.newBuilder()
            .addQueryParameter(QUERY_API_KEY, apiKey)
            .build()
        val request = chain.request().newBuilder()
            .url(interceptedUrl)
            .build()

        return chain.proceed(request)
    }

    companion object {

        private const val QUERY_API_KEY = "api_key"
    }
}