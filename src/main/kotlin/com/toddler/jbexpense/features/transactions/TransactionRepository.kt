package com.toddler.jbexpense.features.transactions

import com.toddler.jbexpense.features.transactions.entity.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<Transaction, Long> {

}

