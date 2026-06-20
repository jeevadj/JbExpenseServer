package com.toddler.jbexpense.features.accounts

import com.toddler.jbexpense.features.users.UsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AccountsService(
    val accountsRepository: AccountsRepository,
    val usersRepository: UsersRepository
) {
    fun getAllAccounts(userId: Long): List<Accounts> {
        return accountsRepository.findByUserId(userId)
    }

    fun createAccount(name: String, balance: Double, userId: Long): Accounts {
        val user = usersRepository.findById(userId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        val account = Accounts(name = name, balance = balance, user = user)
        return accountsRepository.save(account)
    }

    fun updateAccount(id: Long, updatedAccount: Accounts): Accounts? {
        val existingAccount = accountsRepository.findById(id).orElse(null)
        if (existingAccount != null) {
            existingAccount.name = updatedAccount.name
            existingAccount.balance = updatedAccount.balance
            return accountsRepository.save(existingAccount)
        }
        return null
    }

    fun deleteAccount(id: Long) {
        accountsRepository.deleteById(id)
    }

    fun getAccountByIdOrNull(id: Long): Accounts? {
        return accountsRepository.findById(id).orElse(null)
    }
}