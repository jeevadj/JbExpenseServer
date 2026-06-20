package com.toddler.jbexpense.features.transactions

import com.toddler.jbexpense.common.UserContextHolder
import com.toddler.jbexpense.features.transactions.requestParamObj.CreateTransactionRP
import com.toddler.jbexpense.features.transactions.response.CreatedTransactionDTO
import com.toddler.jbexpense.features.transactions.response.GetAllTransactions
import com.toddler.jbexpense.features.transactions.response.toResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transaction")
class TransactionController(
    val transactionService: TransactionService
) {

    @GetMapping
    fun getAllTransactions(): ResponseEntity<GetAllTransactions> {
        val transactionList = transactionService.getAllTransactions()
        val transactionResponseList = transactionList.map { transaction ->
            transaction.toResponse()
        }
        return ResponseEntity(GetAllTransactions(transactionResponseList), HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createTransaction(
        @RequestBody transaction: CreateTransactionRP
    ): ResponseEntity<CreatedTransactionDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        val createdTransaction = transactionService.createTransaction(transaction, userId)
        return ResponseEntity(CreatedTransactionDTO(createdTransaction = createdTransaction), HttpStatus.CREATED)
    }

}

