package com.toddler.jbexpense.features.trip.requestObj

import com.toddler.jbexpense.features.users.Users
import com.toddler.jbexpense.trip.Trip
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

data class CreateTripRP(
    val name: String,
    val description: String,
    val startDate: Long?,
    val endDate: Long?
) {
    companion object {
        fun CreateTripRP.toTrip(user: Users): Trip {
            return Trip(
                name = this.name,
                description = this.description,
                startDate = this.startDate?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneOffset.UTC) },
                endDate = this.endDate?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneOffset.UTC) },
                user = user
            )
        }
    }
}

