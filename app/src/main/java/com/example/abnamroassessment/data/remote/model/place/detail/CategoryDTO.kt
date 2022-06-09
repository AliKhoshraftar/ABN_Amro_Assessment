package com.example.abnamroassessment.data.remote.model.place.detail

import com.example.abnamroassessment.domain.model.place.detail.Category

data class CategoryDTO(
    val icon: IconDTO?,
    val id: Int?,
    val name: String?
)

fun CategoryDTO.toCategory() = Category(
    icon = icon?.toIcon(),
    id = id,
    name = name
)