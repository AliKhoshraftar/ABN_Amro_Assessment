package com.example.abnamroassessment.data.remote.model.place.search

import com.example.abnamroassessment.domain.model.place.search.Places

data class PlacesDTO(
    val context: Context?,
    val results: List<PlaceDTO>?
)

fun PlacesDTO.toPlaces() = Places(
    context = context,
    results = results?.map { it.toPlace() }
)