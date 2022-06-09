package com.example.abnamroassessment.presentation.ui.screen.place.detail

import com.example.abnamroassessment.domain.model.place.detail.PlaceDetail
import com.example.abnamroassessment.presentation.base.ViewEvent
import com.example.abnamroassessment.presentation.base.ViewSideEffect
import com.example.abnamroassessment.presentation.base.ViewState

class PlaceDetailContract {

    sealed class Event : ViewEvent {
        object Retry : Event()
        object BackButtonClicked : Event()
    }

    data class State(
        val place: PlaceDetail?,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            object Back : Navigation()
        }
    }

}
