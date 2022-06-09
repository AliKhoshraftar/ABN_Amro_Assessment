package com.example.abnamroassessment.data.remote.model.place.search

import com.example.abnamroassessment.common.Constants.EMPTY
import com.example.abnamroassessment.domain.model.place.search.Place

data class PlaceDTO(
    val categories: List<Category>?,
    val chains: List<Any>?,
    val distance: Int?,
    val fsq_id: String?,
    val geocodes: Geocodes?,
    val link: String?,
    val location: LocationDTO?,
    val name: String?,
    val related_places: RelatedPlaces?,
    val timezone: String?
)

fun PlaceDTO.toPlace() = Place(
    fsq_id = fsq_id ?: EMPTY,
    distance = distance,
    link = link,
    location = location?.toLocation(),
    name = name,
    timezone = timezone
)