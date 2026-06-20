package com.toddler.jbexpense.features.category

import com.toddler.jbexpense.features.users.UsersRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CategoryService(
    val categoryRepository: CategoryRepository,
    val usersRepository: UsersRepository
) {
    fun getAllCategories(): List<Category> {
        return categoryRepository.findAll().toList()
    }

    fun createCategory(name: String, userId: Long): Category {
        val user = usersRepository.findById(userId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        val category = Category(name = name, user = user)
        return categoryRepository.save(category)
    }

    fun getCategoryByIdOrNull(id: Long): Category? {
        return categoryRepository.findById(id).orElse(null)
    }
}