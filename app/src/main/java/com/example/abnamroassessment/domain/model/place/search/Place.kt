package com.example.abnamroassessment.domain.model.place.search

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.abnamroassessment.domain.model.place.share.Location

@Entity
data class Place(
    @PrimaryKey
    var fsq_id: String,
    var distance: Int?,
    var link: String?,
    @Ignore
    var location: Location?,
    var name: String?,
    var timezone: String?
){
    constructor() : this("", 100, "", null, "", "")
}