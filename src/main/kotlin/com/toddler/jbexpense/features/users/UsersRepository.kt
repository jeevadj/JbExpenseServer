package com.toddler.jbexpense.features.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : CrudRepository<Users, Long> {
    fun findByEmail(email: String): Users?

}