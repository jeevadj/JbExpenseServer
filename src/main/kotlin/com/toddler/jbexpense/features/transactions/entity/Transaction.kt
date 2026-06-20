package com.toddler.jbexpense.features.transactions.entity

import com.toddler.jbexpense.features.accounts.Accounts
import com.toddler.jbexpense.features.category.Category
import com.toddler.jbexpense.features.transactions.TransactionType
import com.toddler.jbexpense.features.users.Users
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
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
@Table(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acct_sq")
    @SequenceGenerator(name = "acct_sq", sequenceName = "act_sequence", allocationSize = 1)
    val id: Long = 0,

    @Column(nullable = false)
    var amount: Double = 0.0,

    @Column
    var description: String?,

    @Column
    var tag: String?,

    @Enumerated(EnumType.STRING)
    val transactionType: TransactionType = TransactionType.EXPENSE,

    @Column(nullable = false)
    var transactionDate: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category?,

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    val accounts: Accounts?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: Users

)