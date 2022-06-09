package com.example.abnamroassessment.data.remote.model.place.detail

import com.example.abnamroassessment.domain.model.place.detail.Icon

data class IconDTO(
    val prefix: String,
    val suffix: String
)

fun IconDTO.toIcon() = Icon(
    prefix = prefix,
    suffix = suffix
)
