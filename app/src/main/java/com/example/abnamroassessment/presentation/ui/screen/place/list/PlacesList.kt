package com.example.abnamroassessment.presentation.ui.screen.place.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.abnamroassessment.domain.model.place.search.Place

@Composable
fun PlacesList(
    places: List<Place>,
    onItemClick: (Place) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(places) { place ->
                PlaceListItem(place = place, onItemClick = onItemClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesListPreview() {
    val users = List(3) { buildPlacePreview() }
    PlacesList(places = users) {}
}