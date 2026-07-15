package com.toddler.jbexpense.features.tripexpense.entity

import com.toddler.jbexpense.features.users.UserDto
import com.toddler.jbexpense.features.users.Users
import com.toddler.jbexpense.features.users.toDto
import com.toddler.jbexpense.trip.Trip
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
@Table(name = "trip_expense")
data class TripExpense(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_expense_sq")
    @SequenceGenerator(name = "trip_expense_sq", sequenceName = "trip_expense_sequence", allocationSize = 1)
    val id: Long = 0,

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = false)
    var amount: Double = 0.0,

    @Column
    var category: String,

    @Column(nullable = false)
    var date: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),

    @Column
    var description: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paid_by", nullable = false)
    var paidBy: Users,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    var trip: Trip,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: Users
)

data class TripExpenseDto(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val category: String? = null,
    val amount: Double = 0.0,
    val date: LocalDateTime? = null,
    val paidBy: UserDto? = null,
)

fun TripExpense.toDto() = TripExpenseDto(
    id          = this.id,
    title       = this.title,
    description = this.description,
    category    = this.category,
    amount      = this.amount,
    date        = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(this.date), java.time.ZoneOffset.UTC),
    paidBy      = this.paidBy.toDto()
)



