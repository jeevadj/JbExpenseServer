package com.toddler.jbexpense.features.trip.response

import com.toddler.jbexpense.GeneralResponse
import com.toddler.jbexpense.trip.Trip
import com.toddler.jbexpense.trip.TripDto

data class GetAllTripResponse(val trips: List<TripDto> = emptyList()) : GeneralResponse()