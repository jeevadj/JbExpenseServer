package com.toddler.jbexpense.config

import com.toddler.jbexpense.common.UserContextInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val userContextInterceptor: UserContextInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userContextInterceptor)
            .addPathPatterns("/api/v1/accounts/**", "/api/v1/category/**", "/api/v1/transaction/**")
    }
}

