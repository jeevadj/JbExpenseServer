package com.toddler.jbexpense.features.transactions

import com.toddler.jbexpense.features.transactions.entity.Transaction
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<Transaction, Long> {

    @Query("SELECT COALESCE(SUM(t.amount), 0.0) FROM Transaction t WHERE t.user.id = :userId AND t.transactionType = :type AND t.transactionDate BETWEEN :startEpoch AND :endEpoch")
    fun sumAmountByUserIdAndTypeAndDateRange(
        userId: Long,
        type: TransactionType,
        startEpoch: Long,
        endEpoch: Long
    ): Double
}

