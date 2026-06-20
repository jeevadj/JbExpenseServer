package com.toddler.jbexpense.features.accounts.dto

import com.toddler.jbexpense.features.accounts.Accounts

data class CreatedAccountDTO(
    val createdAccount: Accounts? = null,
    val message: String = "Account created successfully",
    val result : String = ""
)