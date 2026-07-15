package com.toddler.jbexpense.features.trip.response

import com.toddler.jbexpense.features.trip.entity.TripMember

data class TripMembersDTO(
    val members: List<TripMember> = emptyList(),
    val message: String = "success",
    val result: String = "success"
)

