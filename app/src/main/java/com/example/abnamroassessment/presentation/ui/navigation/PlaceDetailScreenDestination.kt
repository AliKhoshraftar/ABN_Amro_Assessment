package com.example.abnamroassessment.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.abnamroassessment.presentation.ui.screen.place.detail.PlaceDetailContract
import com.example.abnamroassessment.presentation.ui.screen.place.detail.PlaceDetailScreen
import com.example.abnamroassessment.presentation.ui.screen.place.detail.PlaceDetailScreenViewModel

@Composable
fun PlaceDetailScreenDestination(placeId: String, navController: NavController) {
    val viewModel : PlaceDetailScreenViewModel = hiltViewModel()
    PlaceDetailScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is PlaceDetailContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
