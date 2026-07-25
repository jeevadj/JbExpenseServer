package com.toddler.jbexpense.common

import org.springframework.http.HttpStatusCode

/**
 * Custom HTTP status codes for JbExpense.
 *
 * Usage:
 *   throw ResponseStatusException(JbStatusCode.DEFAULT_ACCOUNT_NOT_FOUND.toHttpStatusCode(), "message")
 *   ResponseEntity(body, JbStatusCode.USER_NOT_FOUND.toHttpStatusCode())
 */
enum class JbStatusCode(val code: Int) {

    // 4xx - Client Errors
    ACCOUNT_NOT_FOUND(601),
    CATEGORY_NOT_FOUND(602),
    TRANSACTION_NOT_FOUND(603),
    INVALID_CREDENTIALS(604),
    USER_NOT_FOUND(605),
    DEFAULT_ACCOUNT_NOT_FOUND(606),
    TRIP_NOT_FOUND(607),
    TRIP_EXPENSE_NOT_FOUND(608),

    // 5xx - Server Errors
    ACCOUNT_UPDATE_FAILED(701);

    fun toHttpStatusCode(): HttpStatusCode = HttpStatusCode.valueOf(code)
}