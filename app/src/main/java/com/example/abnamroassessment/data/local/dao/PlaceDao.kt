package com.example.abnamroassessment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abnamroassessment.domain.model.place.search.Place

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    suspend fun getAll(): List<Place>

    @Query("SELECT * FROM place WHERE fsq_id IN (:placeId)")
    suspend fun loadPlace(placeId: String): List<Place>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(places: List<Place>)
}