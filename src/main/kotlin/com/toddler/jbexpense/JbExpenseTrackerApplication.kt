package com.toddler.jbexpense

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JbExpenseTrackerApplication

fun main(args: Array<String>) {
	runApplication<JbExpenseTrackerApplication>(*args)
}
