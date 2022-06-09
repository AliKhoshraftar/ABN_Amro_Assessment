package com.example.abnamroassessment.data.repository

import com.example.abnamroassessment.data.remote.model.place.detail.PlaceDetailDTO
import com.example.abnamroassessment.data.remote.model.place.search.PlacesDTO
import com.example.abnamroassessment.data.remote.retrofit.FourSquareRetrofitService
import com.example.abnamroassessment.domain.repository.PlacesRepository
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val fourSquareRetrofitService: FourSquareRetrofitService
) : PlacesRepository {
    override suspend fun searchPlace(query: String?): PlacesDTO {
        return fourSquareRetrofitService.searchPlace(query = query)
    }

    override suspend fun getPlace(placeId: String): PlaceDetailDTO {
        return fourSquareRetrofitService.placeDetail(id = placeId)
    }
}