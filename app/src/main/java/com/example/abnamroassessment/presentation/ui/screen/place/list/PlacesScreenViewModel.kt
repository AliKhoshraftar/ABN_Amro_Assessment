package com.example.abnamroassessment.presentation.ui.screen.place.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.abnamroassessment.common.Constants.EMPTY
import com.example.abnamroassessment.common.Response
import com.example.abnamroassessment.data.local.dao.PlaceDao
import com.example.abnamroassessment.domain.usecase.seach_places.SearchPlacesUseCase
import com.example.abnamroassessment.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlacesScreenViewModel @Inject constructor(
    private val useCase: SearchPlacesUseCase
) : BaseViewModel<PlacesContract.Event, PlacesContract.State, PlacesContract.Effect>() {

    private val _query: MutableState<String> = mutableStateOf(EMPTY)
    val query: State<String> = _query

    init {
        searchPlaces()
    }

    override fun setInitialState() = PlacesContract.State(
        places = emptyList(),
        isLoading = true,
        isError = false,
    )

    override fun handleEvents(event: PlacesContract.Event) {
        when (event) {
            is PlacesContract.Event.PlaceSelection -> setEffect {
                PlacesContract.Effect.Navigation.ToPlaceDetail(event.place.fsq_id)
            }
            is PlacesContract.Event.Retry -> searchPlaces(query.value)
            is PlacesContract.Event.NewSearch -> searchPlaces(query.value)
        }
    }

    fun searchPlaces(query: String? = null) {
        useCase.execute(query).onEach {
            when (it) {
                is Response.Error -> setState { copy(isError = true, isLoading = false) }
                is Response.Loading -> setState { copy(isLoading = true, isError = false) }
                is Response.Success -> {
                    it.data?.let { places ->
                        setState { copy(places = places, isLoading = false) }
                        setEffect { PlacesContract.Effect.DataWasLoaded }
                    } ?: run {
                        setState { copy(isError = true, isLoading = false) }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onQueryChanged(query: String) {
        _query.value = query
    }
}
