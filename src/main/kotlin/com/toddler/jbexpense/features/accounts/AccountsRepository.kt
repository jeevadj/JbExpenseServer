package com.toddler.jbexpense.features.accounts

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepository : CrudRepository<Accounts, Long> {
    fun findByUserId(userId: Long): List<Accounts>

//    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.account.id = :accountId")
//    fun getTotalSpentByAccount(accountId: Long): BigDecimal?
}