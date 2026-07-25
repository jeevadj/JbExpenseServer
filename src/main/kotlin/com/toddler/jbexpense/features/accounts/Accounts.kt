package com.toddler.jbexpense.features.accounts

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
import java.time.ZoneOffset

@Entity
@Table(name = "accounts")
data class Accounts(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acct_sq")
    @SequenceGenerator(name = "acct_sq", sequenceName = "act_sequence", allocationSize = 1)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var balance: Double = 0.0,

    @Column(nullable = false)
    var updatedAt: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),

    @Column(nullable = false)
    var isDefault: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: Users

)