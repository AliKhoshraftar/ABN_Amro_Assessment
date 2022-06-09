package com.example.abnamroassessment.presentation.ui.screen.place.list

import com.example.abnamroassessment.domain.model.place.search.Place
import com.example.abnamroassessment.presentation.base.ViewEvent
import com.example.abnamroassessment.presentation.base.ViewSideEffect
import com.example.abnamroassessment.presentation.base.ViewState

class PlacesContract {

    sealed class Event : ViewEvent {
        object Retry : Event()
        object NewSearch : Event()
        data class PlaceSelection(val place: Place) : Event()
    }

    data class State(
        val places: List<Place>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToPlaceDetail(val placeId: String?): Navigation()
        }
    }

}
