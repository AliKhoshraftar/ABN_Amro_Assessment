package com.example.abnamroassessment.domain.model.place.search

import com.example.abnamroassessment.data.remote.model.place.search.Context

data class Places(
    val context: Context?,
    val results: List<Place>?
)