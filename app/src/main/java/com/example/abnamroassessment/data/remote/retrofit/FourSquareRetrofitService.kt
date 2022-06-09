package com.example.abnamroassessment.data.remote.retrofit

import com.example.abnamroassessment.data.remote.model.place.detail.PlaceDetailDTO
import com.example.abnamroassessment.data.remote.model.place.search.PlacesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FourSquareRetrofitService {
    @GET("search")
    suspend fun searchPlace(@Query("query") query: String?, @Query("radius") radius: Int = 1000): PlacesDTO

    @GET("{fsq_id}")
    suspend fun placeDetail(@Path("fsq_id") id: String): PlaceDetailDTO
}