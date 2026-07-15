package com.toddler.jbexpense.features.tripexpense.response
import com.toddler.jbexpense.features.tripexpense.entity.TripExpenseDto

data class CreatedTripExpenseDTO(
    val createdTripExpense: TripExpenseDto? = null,
    val message: String = "Trip expense created successfully",
    val result: String = "success"
)
