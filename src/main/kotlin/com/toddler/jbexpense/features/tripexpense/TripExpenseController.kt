package com.toddler.jbexpense.features.tripexpense

import com.toddler.jbexpense.common.UserContextHolder
import com.toddler.jbexpense.features.tripexpense.entity.toDto
import com.toddler.jbexpense.features.tripexpense.requestObj.CreateTripExpenseRP
import com.toddler.jbexpense.features.tripexpense.response.CreatedTripExpenseDTO
import com.toddler.jbexpense.features.tripexpense.response.GetAllTripExpensesDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/tripexpense")
class TripExpenseController(
    val tripExpenseService: TripExpenseService
) {

    @GetMapping
    fun getAllTripExpenses(): ResponseEntity<GetAllTripExpensesDTO> {
        val expenses = tripExpenseService.getAllTripExpenses()
        return ResponseEntity(GetAllTripExpensesDTO(tripExpenses = expenses.map { it.toDto() } ), HttpStatus.OK)
    }

    @GetMapping("/trip/{tripId}")
    fun getTripExpensesByTripId(@PathVariable tripId: Long): ResponseEntity<GetAllTripExpensesDTO> {
        val expenses = tripExpenseService.getTripExpensesByTripId(tripId)
        return ResponseEntity(GetAllTripExpensesDTO(tripExpenses = expenses.map { it.toDto() } ), HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createTripExpense(
        @RequestBody createTripExpenseRP: CreateTripExpenseRP
    ): ResponseEntity<CreatedTripExpenseDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        val createdTripExpense = tripExpenseService.createTripExpense(createTripExpenseRP, userId)
        return ResponseEntity(CreatedTripExpenseDTO(createdTripExpense = createdTripExpense.toDto()), HttpStatus.CREATED)
    }
}
