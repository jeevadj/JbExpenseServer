package com.toddler.jbexpense.features.users.response

import com.toddler.jbexpense.GeneralResponse

data class LoginResponse(
    val name: String? = null,
    val email: String? = null,
    val userId : Long? = null,
    val message: String = "Login successful",
    val result: String = ""
)
