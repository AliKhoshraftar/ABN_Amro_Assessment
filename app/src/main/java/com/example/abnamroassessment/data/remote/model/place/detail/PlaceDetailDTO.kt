package com.example.abnamroassessment.data.remote.model.place.detail

import com.example.abnamroassessment.common.Constants
import com.example.abnamroassessment.domain.model.place.detail.PlaceDetail

data class PlaceDetailDTO(
    val categories: List<CategoryDTO>?,
    val chains: List<Any>?,
    val fsq_id: String?,
    val geocodes: Geocodes?,
    val link: String?,
    val location: LocationDTO?,
    val name: String?,
    val related_places: RelatedPlaces?,
    val timezone: String?
)

fun PlaceDetailDTO.toPlace() = PlaceDetail(
    categories = categories?.map { it.toCategory() },
    fsq_id = fsq_id ?: Constants.EMPTY,
    link = link,
    location = location?.toLocation(),
    name = name,
    timezone = timezone
)