package com.toddler.jbexpense.features.trip.response

import com.toddler.jbexpense.trip.Trip
import com.toddler.jbexpense.trip.TripDto

data class CreatedTripDTO(
    val createdTrip: TripDto? = null,
    val message: String = "Trip created successfully",
    val result: String = "success"
)
