package com.toddler.jbexpense.features.transactions

import com.toddler.jbexpense.common.JbStatusCode
import com.toddler.jbexpense.common.ResourceNotFoundException
import com.toddler.jbexpense.features.accounts.AccountsService
import com.toddler.jbexpense.features.category.CategoryService
import com.toddler.jbexpense.features.transactions.entity.Transaction
import com.toddler.jbexpense.features.transactions.requestParamObj.CreateTransactionRP
import com.toddler.jbexpense.features.transactions.requestParamObj.CreateTransactionRP.Companion.toTransaction
import com.toddler.jbexpense.features.transactions.response.MonthlySummaryDTO
import com.toddler.jbexpense.features.users.UsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneOffset

@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val categoryService: CategoryService,
    val accountsService: AccountsService,
    val usersRepository: UsersRepository
) {

    fun getAllTransactions(): List<Transaction> {
        return transactionRepository.findAll().toList()
    }

    fun createTransaction(createTransactionRP: CreateTransactionRP, userId: Long): Transaction {
        val user = usersRepository.findById(userId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }

        var transaction = createTransactionRP.toTransaction(user)

        createTransactionRP.accountId?.let { accountsService.getAccountByIdOrNull(it) }?.let { account ->
            val updatedBalance = when (transaction.transactionType) {
                TransactionType.EXPENSE -> account.balance - transaction.amount
                TransactionType.INCOME -> account.balance + transaction.amount
            }
            accountsService.updateAccount(account.id, account.copy(balance = updatedBalance))?.let { updatedAccount ->
                transaction = transaction.copy(accounts = updatedAccount)
            } ?: throw IllegalStateException("Failed to update account balance")
        }

        createTransactionRP.categoryId?.let { categoryService.getCategoryByIdOrNull(it) }?.let { category ->
            transaction = transaction.copy(category = category)
        }


        return transactionRepository.save(transaction)
    }

    fun getMonthlySummary(userId: Long): MonthlySummaryDTO {
        val now = LocalDateTime.now()
        val yearMonth = YearMonth.of(now.year, now.month)

        val startEpoch = yearMonth.atDay(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
        val endEpoch = yearMonth.atEndOfMonth().atTime(23, 59, 59, 999_000_000).toInstant(ZoneOffset.UTC).toEpochMilli()

        val totalIncome = transactionRepository.sumAmountByUserIdAndTypeAndDateRange(userId, TransactionType.INCOME, startEpoch, endEpoch)
        val totalExpense = transactionRepository.sumAmountByUserIdAndTypeAndDateRange(userId, TransactionType.EXPENSE, startEpoch, endEpoch)

        val defaultAccount = accountsService.getDefaultAccount(userId)
            ?: throw ResourceNotFoundException(message = "Default account not found for user with ID: $userId", status = JbStatusCode.DEFAULT_ACCOUNT_NOT_FOUND.toHttpStatusCode())

        return MonthlySummaryDTO(
            month = now.monthValue,
            year = now.year,
            totalIncome = totalIncome,
            totalExpense = totalExpense,
            defaultAccountBalance = defaultAccount.balance,
            defaultAccountName = defaultAccount.name,
            result = "success"
        )
    }
}

