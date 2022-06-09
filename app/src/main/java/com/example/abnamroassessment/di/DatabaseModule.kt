package com.example.abnamroassessment.di

import android.content.Context
import androidx.room.Room
import com.example.abnamroassessment.data.local.AppDatabase
import com.example.abnamroassessment.data.local.dao.PlaceDao
import com.example.abnamroassessment.data.local.dao.PlaceDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providePlacesDao(appDatabase: AppDatabase): PlaceDao {
        return appDatabase.placesDao()
    }

    @Provides
    fun providePlaceDetailDao(appDatabase: AppDatabase): PlaceDetailDao {
        return appDatabase.placeDetailDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "PlaceDB"
        ).build()
    }
}