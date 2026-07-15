package com.toddler.jbexpense.features.trip.response

import com.toddler.jbexpense.features.trip.entity.TripMember

data class TripMemberDTO(
    val tripMember: TripMember? = null,
    val message: String = "Trip shared successfully",
    val result: String = "success"
)

