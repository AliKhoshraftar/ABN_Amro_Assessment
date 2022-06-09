package com.example.abnamroassessment.domain.model.place.share

data class Location(
    val address: String?,
    val country: String?,
    val formatted_address: String?,
    val locality: String?,
    val neighborhood: List<String>?,
    val postcode: String?,
    val region: String?
)