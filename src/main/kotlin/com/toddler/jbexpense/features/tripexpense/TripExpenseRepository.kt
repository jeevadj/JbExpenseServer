package com.toddler.jbexpense.features.tripexpense
import com.toddler.jbexpense.features.tripexpense.entity.TripExpense
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
@Repository
interface TripExpenseRepository : CrudRepository<TripExpense, Long> {
    fun findByTripId(tripId: Long): List<TripExpense>
    fun findByUserId(userId: Long): List<TripExpense>
}
