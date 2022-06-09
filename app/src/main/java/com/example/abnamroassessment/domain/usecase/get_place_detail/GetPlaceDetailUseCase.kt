package com.example.abnamroassessment.domain.usecase.get_place_detail

import com.example.abnamroassessment.common.Response
import com.example.abnamroassessment.data.local.dao.PlaceDetailDao
import com.example.abnamroassessment.data.remote.model.place.detail.toPlace
import com.example.abnamroassessment.domain.model.place.detail.PlaceDetail
import com.example.abnamroassessment.domain.repository.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlaceDetailUseCase @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val placeDetailDao: PlaceDetailDao
) {
    fun getOfflineLast(id: String): Flow<Response<PlaceDetail>> = flow {
        try {
            emit(Response.Loading())
            val place = placesRepository.getPlace(id).toPlace()
            placeDetailDao.insert(place)
            emit(Response.Success(place))
        } catch (e: Exception) {
            try {
                val localPlace = placeDetailDao.loadPlace(id)
                localPlace?.let { place ->
                    emit(Response.Success(place))
                } ?: kotlin.run {
                    emit(Response.Error(message = if (e.message != null) e.message else "No Place found"))
                }
            } catch (e:Exception){
                emit(Response.Error(message = if (e.message != null) e.message else "An Unexpected problem occurred"))
            }
        }
    }
}