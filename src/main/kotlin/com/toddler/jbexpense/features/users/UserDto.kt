package com.toddler.jbexpense.features.users

data class UserDto(
    val id: Long = 0,
    val name: String = "",
    val email: String = ""
)

fun Users.toDto() = UserDto(
    id    = this.id,
    name  = this.name,
    email = this.email
)

