package com.toddler.jbexpense.features.accounts

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepository : CrudRepository<Accounts, Long> {
    fun findByUserId(userId: Long): List<Accounts>
    fun findByUserIdAndIsDefaultTrue(userId: Long): Accounts?
}