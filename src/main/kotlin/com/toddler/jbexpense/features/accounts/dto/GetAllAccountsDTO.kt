package com.toddler.jbexpense.features.accounts.dto

import com.toddler.jbexpense.features.accounts.Accounts

data class GetAllAccountsDTO(
    val accounts: List<Accounts> = emptyList()
) {
}