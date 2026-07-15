package com.toddler.jbexpense.features.trip

import com.toddler.jbexpense.features.trip.entity.TripMember
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TripMemberRepository : CrudRepository<TripMember, Long> {
    fun findByUserId(userId: Long): List<TripMember>
    fun findByTripId(tripId: Long): List<TripMember>
    fun existsByTripIdAndUserId(tripId: Long, userId: Long): Boolean
}

