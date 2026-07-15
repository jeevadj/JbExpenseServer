package com.toddler.jbexpense.features.trip

import com.toddler.jbexpense.features.trip.entity.TripMember
import com.toddler.jbexpense.features.trip.requestObj.CreateTripRP
import com.toddler.jbexpense.features.trip.requestObj.CreateTripRP.Companion.toTrip
import com.toddler.jbexpense.features.users.UsersRepository
import com.toddler.jbexpense.trip.Trip
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TripService(
    val tripRepository: TripRepository,
    val tripMemberRepository: TripMemberRepository,
    val usersRepository: UsersRepository
) {

    fun getAllTrip(): List<Trip> {
        return tripRepository.findAll().toList()
    }

    fun createTrip(createTripRP: CreateTripRP, userId: Long): Trip {
        val user = usersRepository.findById(userId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        val trip = createTripRP.toTrip(user)
        return tripRepository.save(trip)
    }

    fun shareTrip(tripId: Long, userEmail: String, requestingUserId: Long): TripMember {
        val trip = tripRepository.findById(tripId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found")
        }
        if (trip.user.id != requestingUserId) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Only the trip owner can share this trip")
        }
        val targetUser = usersRepository.findByEmail(userEmail)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: $userEmail")

        if (targetUser.id == requestingUserId) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot share a trip with yourself")
        }
        if (tripMemberRepository.existsByTripIdAndUserId(tripId, targetUser.id)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Trip is already shared with this user")
        }
        val tripMember = TripMember(trip = trip, user = targetUser)
        return tripMemberRepository.save(tripMember)
    }

    fun getSharedTrips(userId: Long): List<Trip> {
        return tripMemberRepository.findByUserId(userId).map { it.trip }
    }

    fun getTripMembers(tripId: Long): List<TripMember> {
        return tripMemberRepository.findByTripId(tripId)
    }
}