package com.toddler.jbexpense.features.transactions.response

import com.toddler.jbexpense.features.category.Category
import com.toddler.jbexpense.features.transactions.entity.Transaction
import com.toddler.jbexpense.features.transactions.TransactionType

data class TransactionResponse(
    val id: Long,
    val amount: Double,
    val description: String?,
    val tag: String?,
    val transactionType: TransactionType,
    val transactionDate: Long,
    val category: Category?,
    val account: AccountSummary?
)
data class AccountSummary(
    val id: Long,
    val name: String  // only fields you want to expose
)

fun Transaction.toResponse() = TransactionResponse(
    id = id,
    amount = amount,
    description = description,
    tag = tag,
    transactionType = transactionType,
    transactionDate = transactionDate,
    category = category,
    account = accounts?.let { AccountSummary(it.id, it.name) }
)

