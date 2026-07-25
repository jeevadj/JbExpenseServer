package com.toddler.jbexpense.features.transactions.response

import com.toddler.jbexpense.GeneralResponse

data class MonthlySummaryDTO(
    val month: Int,
    val year: Int,
    val totalIncome: Double,
    val totalExpense: Double,
    val defaultAccountBalance: Double?,
    val defaultAccountName: String?,
    override val message: String = "Monthly summary retrieved successfully",
    override val result: String = ""
) : GeneralResponse()

