package com.example.abnamroassessment.presentation.ui.screen.place.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.abnamroassessment.common.Response
import com.example.abnamroassessment.domain.usecase.get_place_detail.GetPlaceDetailUseCase
import com.example.abnamroassessment.presentation.base.BaseViewModel
import com.example.abnamroassessment.presentation.ui.navigation.Navigation.Args.PLACE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlaceDetailScreenViewModel @Inject constructor(
    private val useCase: GetPlaceDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<PlaceDetailContract.Event, PlaceDetailContract.State, PlaceDetailContract.Effect>() {

    init {
        savedStateHandle.get<String>(PLACE_ID)?.let { placeId ->
            getPlace(placeId)
        }
    }

    override fun setInitialState() = PlaceDetailContract.State(
        place = null,
        isLoading = true,
        isError = false,
    )

    override fun handleEvents(event: PlaceDetailContract.Event) {
        when (event) {
            is PlaceDetailContract.Event.Retry -> {
                savedStateHandle.get<String>(PLACE_ID)?.let { placeId ->
                    getPlace(placeId)
                }
            }
            PlaceDetailContract.Event.BackButtonClicked -> setEffect { PlaceDetailContract.Effect.Navigation.Back }
        }
    }

    private fun getPlace(id: String) {
        useCase.getOfflineLast(id).onEach {
            when (it) {
                is Response.Error -> setState { copy(isError = true, isLoading = false) }
                is Response.Loading -> setState { copy(isLoading = true, isError = false) }
                is Response.Success -> {
                    it.data?.let { place ->
                        setState { copy(place = place, isLoading = false) }
                        setEffect { PlaceDetailContract.Effect.DataWasLoaded }
                    } ?: run {
                        setState { copy(isError = true, isLoading = false) }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
