package com.example.abnamroassessment.di

import com.example.abnamroassessment.data.remote.retrofit.FourSquareRetrofitService
import com.example.abnamroassessment.data.repository.PlacesRepositoryImpl
import com.example.abnamroassessment.domain.repository.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlacesRepository(api: FourSquareRetrofitService): PlacesRepository {
        return PlacesRepositoryImpl(api)
    }
}