package com.example.abnamroassessment.presentation.ui.screen.place.list

import com.example.abnamroassessment.MainCoroutineRule
import com.example.abnamroassessment.common.Response
import com.example.abnamroassessment.data.local.dao.PlaceDao
import com.example.abnamroassessment.domain.model.place.search.Place
import com.example.abnamroassessment.domain.usecase.seach_places.SearchPlacesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PlacesScreenViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val searchPlacesUseCase = mockk<SearchPlacesUseCase>()
    private val placeDao = mockk<PlaceDao>()

    @Test
    fun `When searchPlaces initialized then should emit initial view state first`() = runTest {
        val expectedInitialViewState = PlacesContract.State(
            places = emptyList(),
            isLoading = true,
            isError = false
        )

        coEvery { searchPlacesUseCase.execute(null) } returns flow {
            emit(Response.Loading())
        }

        val viewModel = PlacesScreenViewModel(searchPlacesUseCase)

        assertEquals(expectedInitialViewState, viewModel.viewState.value)
    }

    @Test
    fun `When searchPlaces called then should emit a view state`() = runTest {
        val places = listOf(Place(fsq_id = "12345", distance = 123, link = "www.test.com",location = null,name = "title", timezone = null))

        val expectedViewState = PlacesContract.State(
            places = places,
            isLoading = false,
            isError = false
        )

        val response = flowOf(Response.Success(places))

        coEvery { searchPlacesUseCase.execute(null) } returns response

        val viewModel = PlacesScreenViewModel(searchPlacesUseCase)

        assertEquals(expectedViewState, viewModel.viewState.value)
    }

    @Test
    fun `check if searchPlaces call return correct error`() = runTest {
        val expectedViewState = PlacesContract.State(
            places = listOf(),
            isLoading = false,
            isError = true
        )

        coEvery { searchPlacesUseCase.execute(null) } returns flow {
            emit(Response.Error())
        }

        val viewModel = PlacesScreenViewModel(searchPlacesUseCase)

        assertEquals(expectedViewState, viewModel.viewState.value)
    }

}