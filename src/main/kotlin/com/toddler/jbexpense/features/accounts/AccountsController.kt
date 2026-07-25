package com.toddler.jbexpense.features.accounts

import com.toddler.jbexpense.common.UserContextHolder
import com.toddler.jbexpense.features.accounts.dto.CreatedAccountDTO
import com.toddler.jbexpense.features.accounts.dto.GetAllAccountsDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/accounts")
class AccountsController(
    val accountsService: AccountsService,
) {

    @GetMapping
    fun getAllAccounts(): ResponseEntity<GetAllAccountsDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        val accountsList = accountsService.getAllAccounts(userId)
        return ResponseEntity(GetAllAccountsDTO(accountsList), HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createAccount(
        @RequestParam accountName : String?,
        @RequestParam balance : Double?
    ): ResponseEntity<CreatedAccountDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        if(accountName == null || balance == null) {
            return ResponseEntity(CreatedAccountDTO(message = "Request param is null"), HttpStatus.BAD_REQUEST)
        }
        val createdAccount = accountsService.createAccount(accountName, balance, userId)
        return ResponseEntity(CreatedAccountDTO(createdAccount, result = "success"), HttpStatus.CREATED)
    }

    @GetMapping("/default")
    fun getDefaultAccount(): ResponseEntity<CreatedAccountDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        val defaultAccount = accountsService.getDefaultAccount(userId) ?: return ResponseEntity(
            CreatedAccountDTO(
                message = "Default account not found",
                result = "failure"
            ), HttpStatus.NOT_FOUND
        )
        return ResponseEntity(CreatedAccountDTO(defaultAccount, result = "success"), HttpStatus.OK)
    }
    
}