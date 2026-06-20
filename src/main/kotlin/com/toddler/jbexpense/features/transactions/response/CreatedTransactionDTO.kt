package com.toddler.jbexpense.features.transactions.response

import com.toddler.jbexpense.features.transactions.entity.Transaction

data class CreatedTransactionDTO(
    val createdTransaction: Transaction? = null,
    val message: String = "Transaction created successfully",
    val result: String = ""
)

