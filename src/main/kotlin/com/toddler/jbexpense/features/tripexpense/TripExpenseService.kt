package com.toddler.jbexpense.features.tripexpense

import com.toddler.jbexpense.features.tripexpense.entity.TripExpense
import com.toddler.jbexpense.features.tripexpense.requestObj.CreateTripExpenseRP
import com.toddler.jbexpense.features.tripexpense.requestObj.CreateTripExpenseRP.Companion.toTripExpense
import com.toddler.jbexpense.features.trip.TripRepository
import com.toddler.jbexpense.features.users.UsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TripExpenseService(
    val tripExpenseRepository: TripExpenseRepository,
    val tripRepository: TripRepository,
    val usersRepository: UsersRepository
) {

    fun getAllTripExpenses(): List<TripExpense> {
        return tripExpenseRepository.findAll().toList()
    }

    fun getTripExpensesByTripId(tripId: Long): List<TripExpense> {
        return tripExpenseRepository.findByTripId(tripId)
    }

    fun createTripExpense(createTripExpenseRP: CreateTripExpenseRP, userId: Long): TripExpense {
        val user = usersRepository.findById(userId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        val trip = tripRepository.findById(createTripExpenseRP.tripId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found")
        }
        val paidByUser = usersRepository.findById(userId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Paid User not found")
        }
        val tripExpense = createTripExpenseRP.toTripExpense(trip, user, paidByUser)
        return tripExpenseRepository.save(tripExpense)
    }
}
