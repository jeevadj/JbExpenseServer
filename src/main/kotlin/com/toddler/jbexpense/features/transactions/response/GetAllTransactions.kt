package com.toddler.jbexpense.features.transactions.response

data class GetAllTransactions(
    val transactions: List<TransactionResponse> = emptyList()
)

