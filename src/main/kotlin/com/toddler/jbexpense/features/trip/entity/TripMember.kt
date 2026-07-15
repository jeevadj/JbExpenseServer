package com.toddler.jbexpense.features.trip.entity

import com.toddler.jbexpense.features.users.Users
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

@Entity
@Table(name = "trip_member")
data class TripMember(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_member_sq")
    @SequenceGenerator(name = "trip_member_sq", sequenceName = "trip_member_sequence", allocationSize = 1)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    val trip: Trip,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: Users,

    @Column(nullable = false)
    val joinedAt: LocalDateTime = LocalDateTime.now()
)

