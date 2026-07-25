package com.toddler.jbexpense.common

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class ResourceNotFoundException(
    message : String,
    val status : HttpStatusCode,
) : RuntimeException(message)