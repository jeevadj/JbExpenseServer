package com.toddler.jbexpense.features.category

import com.toddler.jbexpense.common.UserContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/category")
class CategoryController(
    val categoryService: CategoryService
) {

    @GetMapping
    fun getAllCategory(): ResponseEntity<AllCategoriesDTO> {
        val categoryList = categoryService.getAllCategories()
        return ResponseEntity(AllCategoriesDTO(categoryList), HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createCategory(
        @RequestParam("categoryName") categoryName: String?
    ): ResponseEntity<CreatedCategoryDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        if(categoryName == null) {
            return ResponseEntity(CreatedCategoryDTO(message = "Request Param null"),HttpStatus.BAD_REQUEST)
        }
        val createdCategory = categoryService.createCategory(name = categoryName, userId = userId)
        return ResponseEntity(CreatedCategoryDTO(category = createdCategory, result = "success"), HttpStatus.CREATED)
    }

}

data class CreatedCategoryDTO(
    val category: Category? = null,
    val message: String = "Account created successfully",
    val result : String = ""
)

data class AllCategoriesDTO(
    val categories: List<Category> = emptyList(),
    val message: String = "Categories retrieved successfully"
)