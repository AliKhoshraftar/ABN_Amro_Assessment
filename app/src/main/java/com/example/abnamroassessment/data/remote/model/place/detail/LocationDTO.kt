package com.example.abnamroassessment.data.remote.model.place.detail

import com.example.abnamroassessment.domain.model.place.share.Location

data class LocationDTO(
    val address: String?,
    val country: String?,
    val cross_street: String?,
    val formatted_address: String?,
    val locality: String?,
    val neighborhood: List<String>?,
    val postcode: String?,
    val region: String?
)

fun LocationDTO.toLocation() = Location(
    address = address,
    country = country,
    formatted_address = formatted_address,
    locality = locality,
    neighborhood = neighborhood,
    postcode = postcode,
    region = region
)