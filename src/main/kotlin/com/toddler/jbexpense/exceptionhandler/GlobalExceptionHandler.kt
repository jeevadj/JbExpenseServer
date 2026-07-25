package com.toddler.jbexpense.exceptionhandler

import com.toddler.jbexpense.common.ErrorResponse
import com.toddler.jbexpense.common.ResourceNotFoundException
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    // Catch specific "Not Found" errors
    @ExceptionHandler(EntityNotFoundException::class, NoSuchElementException::class)
    fun handleNotFound(ex: Exception): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message ?: "Resource not found"
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleCustomNotFound(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = ex.status.value(),
            message = ex.message ?: "Resource not found"
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    // Catch validation errors (e.g., negative amount)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val firstError = ex.bindingResult.allErrors.firstOrNull()?.defaultMessage
        val error = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = firstError ?: "Validation failed"
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    // Catch @RequestParam / @PathVariable constraint violations
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        val firstMessage = ex.constraintViolations.firstOrNull()?.message
        val error = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = firstMessage ?: "Validation failed"
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    // Generic fallback for any other unexpected errors
    @ExceptionHandler(Exception::class)
    fun handleGeneralError(ex: Exception): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "An unexpected error occurred"
        )
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}