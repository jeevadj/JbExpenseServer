package com.toddler.jbexpense.features.users.requestParamObj

import com.toddler.jbexpense.features.users.Users

data class SignUpRp(
    val email: String = "",
    val password: String = "",
    val name : String = "",
) {

    companion object {
        fun SignUpRp.toUser() : Users {
            return Users(
                name = this.name,
                email = this.email,
                password = this.password
            )
        }
    }
}