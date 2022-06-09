package com.example.abnamroassessment.presentation.ui.screen.place.detail

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.abnamroassessment.common.Constants
import com.example.abnamroassessment.domain.model.place.detail.PlaceDetail
import com.example.abnamroassessment.domain.model.place.share.Location
import com.example.abnamroassessment.presentation.base.SIDE_EFFECTS_KEY
import com.example.abnamroassessment.presentation.ui.common.NetworkError
import com.example.abnamroassessment.presentation.ui.common.Progress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PlaceDetailScreen(
    state: PlaceDetailContract.State,
    effectFlow: Flow<PlaceDetailContract.Effect>?,
    onEventSent: (event: PlaceDetailContract.Event) -> Unit,
    onNavigationRequested: (PlaceDetailContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is PlaceDetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(PlaceDetailContract.Effect.Navigation.Back)
                }
                is PlaceDetailContract.Effect.DataWasLoaded -> Unit
            }
        }?.collect()
    }

    Scaffold(
        topBar = { PlaceDetailTopBar {
            onEventSent(PlaceDetailContract.Event.BackButtonClicked)
        } }
    ) {
        when {
            state.isLoading -> Progress()
            state.isError -> NetworkError { onEventSent(PlaceDetailContract.Event.Retry) }
            else -> {
                state.place?.let { placeDetail ->
                    PlaceDetail(
                        placeDetail = placeDetail
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceDetailScreenSuccessPreview() {
    PlaceDetailScreen(
        state = PlaceDetailContract.State(
            place = buildPlaceDetailPreview(),
            isError = false,
            isLoading = false
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@Preview(showBackground = true)
@Composable
fun PlaceDetailScreenErrorPreview() {
    PlaceDetailScreen(
        state = PlaceDetailContract.State(
            isLoading = false,
            place = buildPlaceDetailPreview(),
            isError = true,
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

fun buildPlaceDetailPreview() = PlaceDetail(
    fsq_id = Constants.EMPTY,
    link = null,
    location = Location(address = "Jan van Goyenkade 11", "Netherlands", "Jan van Goyenkade 11", "NL", listOf("Amsterdam, Amstelveen"), "1073 AC", "Region"),
    name = "MCI Amsterdam",
    timezone = "Europe/Amsterdam",
    categories = null
)
