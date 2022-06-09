package com.example.abnamroassessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.abnamroassessment.data.local.dao.PlaceDao
import com.example.abnamroassessment.data.local.dao.PlaceDetailDao
import com.example.abnamroassessment.domain.model.place.detail.PlaceDetail
import com.example.abnamroassessment.domain.model.place.search.Place

@Database(entities = [Place::class, PlaceDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placesDao(): PlaceDao
    abstract fun placeDetailDao(): PlaceDetailDao
}