package com.example.abnamroassessment.domain.usecase.seach_places

import com.example.abnamroassessment.common.Response
import com.example.abnamroassessment.data.local.dao.PlaceDao
import com.example.abnamroassessment.data.remote.model.place.search.toPlaces
import com.example.abnamroassessment.domain.model.place.search.Place
import com.example.abnamroassessment.domain.repository.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchPlacesUseCase @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val placeDao: PlaceDao
) {
    fun execute(query: String?): Flow<Response<List<Place>>> = flow {
        try {
            emit(Response.Loading())
            placesRepository.searchPlace(query).toPlaces().results?.let { places ->
                placeDao.insertAll(places)
                emit(Response.Success(places))
            } ?: run {
                emit(Response.Error(message = "Empty Result"))
            }
        } catch (e: HttpException) {
            emit(Response.Error(message = if (e.message != null) e.message else "An Unexpected problem occurred"))
        } catch (e: IOException) {
            emit(Response.Error(message = if (e.message != null) e.message else "Please Check your internet connectivity"))
        }
    }
}