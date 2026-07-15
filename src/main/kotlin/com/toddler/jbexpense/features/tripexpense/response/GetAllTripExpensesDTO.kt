package com.toddler.jbexpense.features.tripexpense.response
import com.toddler.jbexpense.GeneralResponse
import com.toddler.jbexpense.features.tripexpense.entity.TripExpense
import com.toddler.jbexpense.features.tripexpense.entity.TripExpenseDto

data class GetAllTripExpensesDTO(
    val tripExpenses: List<TripExpenseDto> = emptyList()
) : GeneralResponse()
