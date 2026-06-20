package com.toddler.jbexpense.features.users

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

@Service
class UsersService(
    val usersRepository: UsersRepository,
    val passwordEncoder: PasswordEncoder
) {

    fun createUser(user: Users): Users {
        val encryptedUser = user.copy(password = passwordEncoder.encode(user.password)!!)
        return usersRepository.save(encryptedUser)
    }

    fun validateUserCredentials(email: String, password: String): Pair<Boolean, Users?> {
        val user = usersRepository.findByEmail(email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: $email")

        return Pair( passwordEncoder.matches(password, user.password),user)
    }

    fun getUserByEmail(email: String): Users {
        return usersRepository.findByEmail(email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: $email")
    }
}
