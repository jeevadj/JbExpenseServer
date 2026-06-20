package com.toddler.jbexpense.features.category

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

@Entity
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acct_sq")
    @SequenceGenerator(name = "acct_sq", sequenceName = "act_sequence", allocationSize = 1)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var isDefault: Boolean = false,

    @Column(nullable = false)
    var iconUrl : String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: Users
)