package com.example.abnamroassessment.domain.model.place.detail

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.abnamroassessment.domain.model.place.share.Location

@Entity
data class PlaceDetail(
    @PrimaryKey
    var fsq_id: String,
    @Ignore
    var categories: List<Category>?,
    var link: String?,
    @Ignore
    var location: Location?,
    var name: String?,
    var timezone: String?
){
    constructor() : this("",null, "", null, "", "")
}