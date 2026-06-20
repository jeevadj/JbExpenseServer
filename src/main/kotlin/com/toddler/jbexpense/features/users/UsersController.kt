package com.toddler.jbexpense.features.users

import com.toddler.jbexpense.GeneralResponse
import com.toddler.jbexpense.features.users.requestParamObj.SignUpRp
import com.toddler.jbexpense.features.users.requestParamObj.SignUpRp.Companion.toUser
import com.toddler.jbexpense.features.users.response.LoginResponse
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Validated
@RestController
@RequestMapping("/api/v1/user")
class UsersController(
    val usersService: UsersService
) {

    @PostMapping("/signup")
    fun createUser(@RequestBody user: SignUpRp): ResponseEntity<GeneralResponse> {
        val createdUser = usersService.createUser(user.toUser())
        val response = GeneralResponse("User created successfully", "success")
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun loginUser(
        @RequestParam("email") @NotBlank(message = "Email must not be blank") @Email(message = "Email must be a valid email address") email: String,
        @RequestParam("password") @NotBlank(message = "Password must not be blank") @Size(min = 8, message = "Password must be at least 8 characters") password: String
    ): ResponseEntity<LoginResponse> {
        val userPair = usersService.validateUserCredentials(email, password)
        return if (userPair.first) {
            val login = LoginResponse(name = userPair.second?.name, email = userPair.second?.email, userId = userPair.second?.id ,"Login successful","success")
            ResponseEntity(login,HttpStatus.OK)
        } else {
            val login = LoginResponse(message = "Login failed", result = "failed")
            ResponseEntity( login,HttpStatus.BAD_REQUEST)
        }
    }

}