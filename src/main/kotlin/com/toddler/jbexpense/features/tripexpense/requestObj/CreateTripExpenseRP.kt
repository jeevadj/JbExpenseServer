package com.toddler.jbexpense.features.tripexpense.requestObj
import com.toddler.jbexpense.features.tripexpense.entity.TripExpense
import com.toddler.jbexpense.features.users.Users
import com.toddler.jbexpense.trip.Trip
import java.time.LocalDateTime
import java.time.ZoneOffset
data class CreateTripExpenseRP(
    val tripId: Long,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Long?,
    val description: String,
    val paidBy: String?
) {
    companion object {
        fun CreateTripExpenseRP.toTripExpense(trip: Trip, user: Users, paidBy: Users?): TripExpense {
            return TripExpense(
                title = this.title,
                amount = this.amount,
                category = this.category,
                date = this.date ?: LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
                description = this.description,
                paidBy = paidBy ?: user,
                trip = trip,
                user = user
            )
        }
    }
}
