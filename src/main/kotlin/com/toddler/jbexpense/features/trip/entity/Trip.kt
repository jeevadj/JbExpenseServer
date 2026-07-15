package com.toddler.jbexpense.trip

import com.toddler.jbexpense.features.users.Users
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

@Entity
@Table(name = "trip")
data class Trip(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acct_sq")
    @SequenceGenerator(name = "acct_sq", sequenceName = "act_sequence", allocationSize = 1)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column
    var description: String,

    @Column
    var startDate: LocalDateTime? = null,

    @Column
    var endDate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: Users
)

data class TripDto(
    val id: Long                    = 0,
    val name: String                = "",
    val description: String         = "",
    val startDate: LocalDateTime?   = null,
    val endDate: LocalDateTime?     = null
)

fun Trip.toDto() = TripDto(
    id          = this.id,
    name        = this.name,
    description = this.description,
    startDate   = this.startDate,
    endDate     = this.endDate
)