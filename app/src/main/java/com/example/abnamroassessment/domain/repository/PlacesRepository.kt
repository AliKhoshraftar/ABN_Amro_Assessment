package com.example.abnamroassessment.domain.repository

import com.example.abnamroassessment.data.remote.model.place.detail.PlaceDetailDTO
import com.example.abnamroassessment.data.remote.model.place.search.PlacesDTO

interface PlacesRepository {
    suspend fun searchPlace(query: String?): PlacesDTO
    suspend fun getPlace(placeId: String): PlaceDetailDTO
}