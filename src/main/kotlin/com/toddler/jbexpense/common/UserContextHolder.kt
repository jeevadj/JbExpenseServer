package com.toddler.jbexpense.common

object UserContextHolder {
    private val userIdHolder = ThreadLocal<String>()

    fun setUserId(userId: String) = userIdHolder.set(userId)
    fun getUserId(): String? = userIdHolder.get()
    fun clear() = userIdHolder.remove()
}

