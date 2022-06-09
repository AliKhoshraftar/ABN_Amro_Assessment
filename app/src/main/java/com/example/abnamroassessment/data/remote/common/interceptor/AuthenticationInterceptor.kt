package com.example.abnamroassessment.data.remote.common.interceptor

import com.example.abnamroassessment.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        val newRequest = it.newBuilder()
            .addHeader("Authorization", BuildConfig.API_KEY)
            .build()

        chain.proceed(newRequest)
    }
}