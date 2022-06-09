package com.example.abnamroassessment.di

import com.example.abnamroassessment.common.Constants
import com.example.abnamroassessment.data.remote.common.interceptor.ApiLogger
import com.example.abnamroassessment.data.remote.common.interceptor.AuthenticationInterceptor
import com.example.abnamroassessment.data.remote.retrofit.FourSquareRetrofitService
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

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            // Add pass apiKey for every api request instead of putting on every retrofit methods
            // Also Api key is added to local.properties to be more secure (local.properties normally won't add to git)
            .addInterceptor(AuthenticationInterceptor())
            .addInterceptor(HttpLoggingInterceptor(ApiLogger()).apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideFoursquareRetrofitService(okHttpClient: OkHttpClient): FourSquareRetrofitService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FourSquareRetrofitService::class.java)
    }
}