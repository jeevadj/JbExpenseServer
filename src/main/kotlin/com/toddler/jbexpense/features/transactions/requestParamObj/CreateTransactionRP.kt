package com.toddler.jbexpense.features.transactions.requestParamObj

import com.toddler.jbexpense.features.transactions.entity.Transaction
import com.toddler.jbexpense.features.transactions.TransactionType
import com.toddler.jbexpense.features.users.Users
import java.time.LocalDateTime
import java.time.ZoneOffset

data class CreateTransactionRP(
    val amount: Double,
    val description: String,
    val transactionType: Int?,
    val tags: String?,
    val transactionDate: Long?,
    val accountId: Long?,
    val categoryId: Long?,
) {
    companion object {
        fun CreateTransactionRP.toTransaction(user: Users): Transaction {
            return Transaction(
                amount = this.amount,
                description = this.description,
                tag = this.tags,
                transactionType = TransactionType.entries.find { it.type == this.transactionType } ?: TransactionType.EXPENSE,
                transactionDate = this.transactionDate ?: LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
                category = null,
                accounts = null,
                user = user
            )
        }
    }
}

