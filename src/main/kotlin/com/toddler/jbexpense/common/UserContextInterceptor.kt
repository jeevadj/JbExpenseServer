package com.toddler.jbexpense.common

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class UserContextInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val userId = request.getHeader("X-User-Id")
        if (userId.isNullOrBlank()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            response.contentType = "application/json"
            response.writer.write("""{"message": "X-User-Id header is required"}""")
            return false
        }
        UserContextHolder.setUserId(userId)
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        UserContextHolder.clear()
    }
}

