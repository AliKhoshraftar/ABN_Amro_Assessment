package com.example.abnamroassessment.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.abnamroassessment.presentation.ui.screen.place.list.PlacesContract
import com.example.abnamroassessment.presentation.ui.screen.place.list.PlacesScreen
import com.example.abnamroassessment.presentation.ui.screen.place.list.PlacesScreenViewModel

@ExperimentalComposeUiApi
@Composable
fun PlacesScreenDestination(
    navController: NavController,
    viewModel: PlacesScreenViewModel = hiltViewModel()
) {
    PlacesScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        query = viewModel.query,
        onQueryChanged = { query -> viewModel.onQueryChanged(query) }
    ) { navigationEffect ->
        if (navigationEffect is PlacesContract.Effect.Navigation.ToPlaceDetail) {
            navController.navigateToPlaceDetail(navigationEffect.placeId)
        }
    }
}
