package com.example.abnamroassessment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abnamroassessment.domain.model.place.detail.PlaceDetail

@Dao
interface PlaceDetailDao {
    @Query("SELECT * FROM placedetail")
    suspend fun getAll(): List<PlaceDetail>

    @Query("SELECT * FROM placedetail WHERE fsq_id = :placeId")
    suspend fun loadPlace(placeId: String): PlaceDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(placeDetail: PlaceDetail)
}