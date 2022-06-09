package com.example.abnamroassessment.presentation.ui.screen.place.list

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.abnamroassessment.common.Constants.EMPTY
import com.example.abnamroassessment.domain.model.place.search.Place
import com.example.abnamroassessment.domain.model.place.share.Location
import com.example.abnamroassessment.presentation.base.SIDE_EFFECTS_KEY
import com.example.abnamroassessment.presentation.ui.common.NetworkError
import com.example.abnamroassessment.presentation.ui.common.Progress
import kotlinx.coroutines.flow.Flow

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlacesScreen(
    state: PlacesContract.State,
    effectFlow: Flow<PlacesContract.Effect>?,
    onEventSent: (event: PlacesContract.Event) -> Unit,
    query: State<String>,
    onQueryChanged: (query: String) -> Unit,
    onNavigationRequested: (navigationEffect: PlacesContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect(collector = {
            when (it) {
                is PlacesContract.Effect.DataWasLoaded -> Unit
                is PlacesContract.Effect.Navigation.ToPlaceDetail -> onNavigationRequested(it)
            }
        })
    }

    Scaffold(
        topBar = {
            SearchView(
                query = query.value,
                onQueryChanged = onQueryChanged,
                onExecuteSearch = {
                    onEventSent(PlacesContract.Event.NewSearch)
                },
            )
        }
    ) {
        when {
            state.isLoading -> Progress()
            state.isError -> NetworkError { onEventSent(PlacesContract.Event.Retry) }
            else -> PlacesList(places = state.places) {
                onEventSent(
                    PlacesContract.Event.PlaceSelection(
                        it
                    )
                )
            }
        }
    }
}

fun buildPlacePreview() = Place(
    fsq_id = EMPTY,
    distance = 100,
    link = null,
    location = Location(address = "Jan van Goyenkade 11", null, null, null, null, null, null),
    name = "MCI Amsterdam",
    timezone = "Europe/Amsterdam"
)
