package com.example.abnamroassessment.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.abnamroassessment.presentation.ui.navigation.Navigation.Args.PLACE_ID

@ExperimentalComposeUiApi
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.PLACES
    ) {
        composable(
            route = Navigation.Routes.PLACES
        ) {
            PlacesScreenDestination(navController)
        }

        composable(
            route = Navigation.Routes.DETAIL,
            arguments = listOf(navArgument(name = PLACE_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val placeId = requireNotNull(backStackEntry.arguments?.getString(PLACE_ID)) { "Place id is required as an argument" }
            PlaceDetailScreenDestination(
                placeId = placeId,
                navController = navController
            )
        }
    }
}

object Navigation {

    object Args {
        const val PLACE_ID = "place_id"
    }

    object Routes {
        const val PLACES = "places"
        const val DETAIL = "$PLACES/{$PLACE_ID}"
    }

}

fun NavController.navigateToPlaceDetail(placeId: String?) {
    navigate(route = "${Navigation.Routes.PLACES}/$placeId")
}
