package com.toddler.jbexpense.features.trip

import com.toddler.jbexpense.trip.Trip
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TripRepository : CrudRepository<Trip, Long> {

}