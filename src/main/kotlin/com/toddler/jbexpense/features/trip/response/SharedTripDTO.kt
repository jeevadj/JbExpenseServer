package com.toddler.jbexpense.features.trip.response

import com.toddler.jbexpense.trip.Trip

data class SharedTripDTO(
    val trips: List<Trip> = emptyList(),
    val message: String = "success",
    val result: String = "success"
)

